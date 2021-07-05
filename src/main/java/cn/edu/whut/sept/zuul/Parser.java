package cn.edu.whut.sept.zuul;

import java.util.Scanner;

/**
 * 这个类是“World of Zuul”应用程序的一部分。《World of Zuul》是一款非常简单的文本冒险游戏.
 * <p>
 * 该解析器读取用户输入并将其解释为“冒险”命令。每次调用它时，它从终端读取一行并尝试将该行解释为一个两个单词的命令。它返回命令
 * 作为命令类的对象。
 * <p>
 * 解析器有一组已知的命令字。它检查用户输入已知的命令，如果输入的不是已知的命令之一，它返回一个标记为未知命令的命令对象。
 *
 * @author yzh
 * @version 2021.06.28
 * @since 1.0
 */
public class Parser {
    /**
     * 保存所有有效的命令.
     */
    private CommandWords commands;
    /**
     * 命令输入的来源.
     */
    private Scanner reader;

    /**
     * 创建从终端窗口读取的解析器.
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * @return 返回来自用户的命令。
     */
    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        System.out.print("> ");     // 打印提示

        inputLine = reader.nextLine();

        // 在一行上找出最多两个单词。
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();      // 得到第一个词
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();      // 得到第二个单词
                // note:我们只是忽略了输入行的其余部分。
            }
        }

        // 现在检查这个单词是否已知。如果是，用它创建一个命令。如果不是，创建一个“null”命令(对于未知的命令)。.
        if (commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }

    /**
     * 打印一列所有已知的有效的命令字符.
     */
    public void showCommands() {
        commands.showAll();
    }
}

