package com.skl.mvpdome;

public class AndriodManager {
//    private static AndriodManager mInstance = null;
//
//    public static AndriodManager getInstance() {
//        if (mInstance == null) {
//            synchronized (AndriodManager.class) {
//                if (mInstance == null) {
//                    mInstance = new AndriodManager();
//                }
//            }
//        }
//        return mInstance;
//    }

    private static class Holder{
        private static final AndriodManager INSTANCE = new AndriodManager();
    }

    public static AndriodManager getInstance(){
        return Holder.INSTANCE;
    }

}
