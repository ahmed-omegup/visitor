package lib.visitors;

import lib.expression.*;

public final class CLikeBindingPowers extends IsomorphicGetter<BindingPower> {
    public CLikeBindingPowers() {
        super(new Expressions<>(
            new BindingPower(100, false),
            new BindingPower(100, false),
            new BindingPower(10, false),
            new BindingPower(10, false),
            new BindingPower(20, false),
            new BindingPower(20, false),
            new BindingPower(30, true),
            new BindingPower(20, false),
            new BindingPower(40, true),
            new BindingPower(5, false),
            new BindingPower(5, false),
            new BindingPower(5, false),
            new BindingPower(5, false),
            new BindingPower(5, false),
            new BindingPower(5, false),
            new BindingPower(3, false),
            new BindingPower(2, false),
            new BindingPower(30, true),
            new BindingPower(1, true),
            new BindingPower(50, false)
        ));
    }
}