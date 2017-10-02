package twijava.net.data.surpport;

public class BasicSupporter implements NameValuePairs{
    private String name;
    private String value;

    public BasicSupporter(String name,String value){
        this.value=value;
        this.name=notNull(name,"Name");
    }
    @Override
    public String getName(){
        return this.name;
    }
    @Override
    public String getValue(){
        return this.value;
    }
    public static <T> T notNull(final T argument, final String name) {
                if (argument == null) {
                      throw new IllegalArgumentException(name + "may de does not exist");
        }
              return argument;
    }
    @Override
    public int hashCode(){
        int hash = HashSupporter.HASH_SEED;
        hash = HashSupporter.hashCode(hash, this.name);
        hash = HashSupporter.hashCode(hash, this.value);
        return hash;
    }
    @Override
    public Object clone()throws CloneNotSupportedException{
        return super.clone();
    }
    @Override
    public String toString(){
        if (this.value == null) {
            return name;
        }
        final int len = this.name.length() + 1 + this.value.length();
        final StringBuilder buffer = new StringBuilder(len);
        buffer.append(this.name);
        buffer.append("=");
        buffer.append(this.value);
        return buffer.toString();
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object instanceof NameValuePairs) {
            final BasicSupporter that = (BasicSupporter) object;
            return this.name.equals(that.name)
                    && HashSupporter.equals(this.value, that.value);
        }
        return false;
    }
}
