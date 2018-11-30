package com.mihoyo.hk4e.wechat.unimport;

/**
 * 一个枚举类的例子
 * 献给我沙雕老婆
 */
public enum Shape {
    CIRCULAR("圆形"),

    RECTANGLE("矩形"){
        @Override
        public int getVerticeNum() {
            return 4;
        }
    },

    TRIANGLE("三角形"){
        @Override
        public int getVerticeNum() {
            return 3;
        }
    };


    private String chineseName;

    Shape(String chineseName){
        this.chineseName = chineseName;
    }

    /**
     * 返回顶点数
     * @return
     * 这个方法其实可以看做是一个
     * CIRCULAR RECTANGLE TRIANGLE的父类的实现
     * 所以他们可以各自override它来实现自身的逻辑
     * 因为圆的返回值就是0 所以可以不用实现 就用父类的
     */
    public int getVerticeNum(){
        return 0;
    }


    public static void main(String[] args){
        System.out.println(CIRCULAR.getVerticeNum());
        System.out.println(RECTANGLE.getVerticeNum());
        System.out.println(TRIANGLE.getVerticeNum());
    }

}
