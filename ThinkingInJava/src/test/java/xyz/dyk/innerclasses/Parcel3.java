package xyz.dyk.innerclasses;

public class Parcel3 extends Parcel2 {
    class Contents extends Parcel2.Contents {
        private int i = 11;

        public int value() {
            return i;
        }
    }
}
