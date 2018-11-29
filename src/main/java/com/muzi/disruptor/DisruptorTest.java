package com.muzi.disruptor;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorTest {

    /**
     * 事件(Event)就是通过 Disruptor 进行交换的数据类型。
     */
    public static class LongEvent
    {
        private long value;

        public void set(long value)
        {
            this.value = value;
        }

        public long getValue() {
            return value;
        }
    }

    /**
     * 事件工厂(Event Factory)定义了如何实例化前面第1步中定义的事件(Event)，需要实现接口 com.lmax.disruptor.EventFactory<T>。
     * Disruptor 通过 EventFactory 在 RingBuffer 中预创建 Event 的实例。
     *
     * 一个 Event 实例实际上被用作一个“数据槽”，发布者发布前，先从 RingBuffer 获得一个 Event 的实例，然后往 Event 实例中填充数据，
     * 之后再发布到 RingBuffer 中，之后由 Consumer 获得该 Event 实例并从中读取数据。
     */
    public static class LongEventFactory implements EventFactory<LongEvent> {
        public LongEvent newInstance() {
            return new LongEvent();
        }
    }

    /**
     * 通过实现接口 com.lmax.disruptor.EventHandler<T> 定义事件处理的具体实现。
     */
    public static class LongEventHandler implements EventHandler<LongEvent> {
        public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
            System.out.println("Event:" + longEvent.getValue());
        }
    }

    public static class LongEventProducerWithTranslator
    {
        private final RingBuffer<LongEvent> ringBuffer;

        public LongEventProducerWithTranslator(RingBuffer<LongEvent> ringBuffer)
        {
            this.ringBuffer = ringBuffer;
        }

        private static final EventTranslatorOneArg<LongEvent, ByteBuffer> TRANSLATOR =
                new EventTranslatorOneArg<LongEvent, ByteBuffer>()
                {
                    public void translateTo(LongEvent event, long sequence, ByteBuffer bb)
                    {
                        event.set(bb.getLong(0));
                    }
                };

        public void onData(ByteBuffer bb)
        {
            ringBuffer.publishEvent(TRANSLATOR, bb);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        /**
         * Disruptor 通过 java.util.concurrent.ExecutorService 提供的线程来触发 Consumer 的事件处理。
         */

        /**
         * Disruptor 定义了 com.lmax.disruptor.WaitStrategy 接口用于抽象 Consumer 如何等待新事件，这是策略模式的应用。
         *
         * Disruptor 提供了多个 WaitStrategy 的实现，每种策略都具有不同性能和优缺点，根据实际运行环境的 CPU 的硬件特点选择恰当的策略，并配合特定的 JVM 的配置参数，能够实现不同的性能提升。
         *
         * 例如，BlockingWaitStrategy、SleepingWaitStrategy、YieldingWaitStrategy 等，其中，
         *
         * BlockingWaitStrategy 是最低效的策略，但其对CPU的消耗最小并且在各种不同部署环境中能提供更加一致的性能表现；
         * SleepingWaitStrategy 的性能表现跟 BlockingWaitStrategy 差不多，对 CPU 的消耗也类似，但其对生产者线程的影响最小，适合用于异步日志类似的场景；
         * YieldingWaitStrategy 的性能是最好的，适合用于低延迟的系统。在要求极高性能且事件处理线数小于 CPU 逻辑核心数的场景中，推荐使用此策略；例如，CPU开启超线程的特性。
         */

        EventFactory<LongEvent> eventFactory = new LongEventFactory();

        int ringBufferSize = 1024 * 1024;

        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(eventFactory, ringBufferSize, DaemonThreadFactory.INSTANCE);

        EventHandler<LongEvent> eventHandler = new LongEventHandler();
        disruptor.handleEventsWith(eventHandler);
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducerWithTranslator translator = new LongEventProducerWithTranslator(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++)
        {
            bb.putLong(0, l);
            translator.onData(bb);
            Thread.sleep(1000);
        }
    }
}
