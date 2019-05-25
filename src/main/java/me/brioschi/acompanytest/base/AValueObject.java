package me.brioschi.acompanytest.base;

public abstract class AValueObject {

    private final Class targetClass;

    protected AValueObject(Class targetClass) {
        this.targetClass = targetClass;
    }

    protected abstract int valueObjectHashCode();

    @Override
    public boolean equals(Object other) {
        return  (other != null) &&
                (targetClass.isInstance(other) ) &&
                (other.hashCode() == this.hashCode())
                ;
    }

    @Override
    public int hashCode() {
        return valueObjectHashCode();
    }

}
