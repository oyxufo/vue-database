package cn.edu.whut.sept.zuul;

/**
 * 这个类是“World of Zuul”应用程序的命令类.
 * 初始化一个CommandWords对象，该类主要功能是提供判断是否为已有的命令，以及输出所有已有的命令类型.
 *
 * @author yzh
 * @version 2021.06.28
 */
public class CommandWords {
    /**
     * 静态常量，表示命令的几种形式.
     */
    private static final String[] VALID_COMMANDS = {
            "go", "quit", "help"
    };

    /**
     * 构造函数.
     */
    public CommandWords() {
        // nothing to do at the moment...
    }

    /**
     * 根据存在的命令与输入的命令一一比较，判读命令是否合法.
     * @param aString 来源于Parser包中的word1,即输入的第一个单词
     * @return 如果该命令存在于validCommands数组中，则返回true,否则返回false
     */
    public boolean isCommand(final String aString) {
        for (int i = 0; i < VALID_COMMANDS.length; i++) {
            //与validCommands一一进行比较，判读是否为已有的命令
            if (VALID_COMMANDS[i].equals(aString)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 输出所有的已存在的命令类型.
     */
    public void showAll() {
        for (String command : VALID_COMMANDS) {
            System.out.print(command + "  ");
        }
        System.out.println();
    }
}
