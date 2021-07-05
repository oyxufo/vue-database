package cn.edu.whut.sept.zuul;

/**
 * 这个类是“World of Zuul”应用程序的启动入口.
 * 用户通过该类启动程序
 *
 * @author yzh
 * @version 2021.06.28
 */
public class Main {
    /**
     * 程序的启动入口.
     *
     * @param args
     */
    public static void main(final String[] args) {
        Game game = new Game(); //初始化游戏
        game.play();
    }
}
