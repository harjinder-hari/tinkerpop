package com.tinkerpop.gremlin.process.graph.step.sideEffect;

import com.tinkerpop.gremlin.process.Traversal;
import com.tinkerpop.gremlin.process.Traverser;
import com.tinkerpop.gremlin.process.graph.marker.Reversible;
import com.tinkerpop.gremlin.process.util.AbstractStep;

import java.util.function.Consumer;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class SideEffectStep<S> extends AbstractStep<S, S> implements Reversible {

    private Consumer<Traverser<S>> consumer = null;

    public SideEffectStep(final Traversal traversal) {
        super(traversal);
    }

    public void setConsumer(final Consumer<Traverser<S>> consumer) {
        this.consumer = consumer;
    }

    @Override
    protected Traverser<S> processNextStart() {
        final Traverser<S> traverser = this.starts.next();
        if (null != this.consumer) this.consumer.accept(traverser);
        return traverser;
    }
}
