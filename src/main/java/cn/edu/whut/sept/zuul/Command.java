package cn.edu.whut.sept.zuul;

/**
 * 这个类是“World of Zuul”应用程序的命令类.
 * 初始化一个Command对象，需要一个或两个单词，该类提供返回两个单词的方法以及判断是否为非法命令
 *
 * @author yzh
 * @version 2021.06.28
 */
public class Command {
    /**
     * 输入命令的第一个单词.
     */
    private String commandWord;
    /**
     * 输入命令的第二个单词.
     */
    private String secondWord;

    /**
     * 创建命令对象并初始化两个单词.
     * @param firstword  第一个单词
     * @param secondword 第二个单词
     */
    public Command(final String firstword, final String secondword) {
        commandWord = firstword;
        this.secondWord = secondword;
    }

    /**
     * 获取命令的类型.
     * @return commandWord为第一个单词，即返回该命令的类型
     */
    public String getCommandWord() {
        return commandWord;
    }

    /**
     * 返回命令的参数.
     * @return secondWord为第二个单词，即返回该命令的参数
     */
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * 判断是否为未知命令，如果commandWord为null，说明为非法命令.
     *
     * @return 返回是否为未知命令，是则返回true,不是返回false
     */
    public boolean isUnknown() {
        return (commandWord == null);
    }

    /**
     * 判断是否有第二个单词.
     * @return 判断是否有第二个单词，是则返回true,不是返回false
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}
