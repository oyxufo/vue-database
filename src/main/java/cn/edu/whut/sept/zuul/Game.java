package cn.edu.whut.sept.zuul;

/**
 * 该类是“World-of-Zuul”应用程序的主类.
 * 《World of Zuul》是一款简单的文本冒险游戏。用户可以在一些房间组成的迷宫中探险。
 * 你们可以通过扩展该游戏的功能使它更有趣!.
 * <p>
 * 如果想开始执行这个游戏，用户需要创建Game类的一个实例并调用“play”方法。
 * <p>
 * Game类的实例将创建并初始化所有其他类:它创建所有房间，并将它们连接成迷宫；它创建解析器
 * 接收用户输入，并将用户输入转换成命令后开始运行游戏。
 *
 * @author Michael Kölling and David J. Barnes
 * @version 1.0
 */
public class Game {
    /**
     * parser为一个解析器对象.
     */
    private Parser parser;
    /**
     * currentRoom为当前房间.
     */
    private Room currentRoom;

    /**
     * 创建游戏并初始化内部数据和解析器.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * 枚举类型，会根据输入的命令选择相应的函数.
     */
    private enum CommandEnum {
        /**
         * Go命令的具体执行.
         */
        GO("go") {
            @Override
            public Object apply(final Command command, final Game game) {
                if (!command.hasSecondWord()) {
                    // 如果没有第二个单词，就不知道到哪去
                    System.out.println("Go where?");
                    return false;
                }
                String direction = command.getSecondWord();

                // 试着根据方向离开当前的房间。
                Room nextRoom = game.currentRoom.getExit(direction);

                if (nextRoom == null) {
                    System.out.println("There is no door!");
                } else {
                    game.currentRoom = nextRoom;
                    System.out.println(game.currentRoom.getLongDescription());
                }
                return false;
            }
        },
        /**
         * HELP命令的具体执行.
         */
        HELP("help") {
            @Override
            public Object apply(final Command command, final Game game) {
                System.out.println("You are lost. You are alone. You wander");
                System.out.println("around at the university.");
                System.out.println();
                System.out.println("Your command words are:");
                game.parser.showCommands();
                return false;
            }
        },
        /**
         * QUIT命令的具体执行.
         */
        QUIT("quit") {
            @Override
            public Object apply(final Command command, final Game game) {
                if (command.hasSecondWord()) {
                    System.out.println("Quit what?");
                    return false;
                } else {
                    return true;  // 表示想要退出
                }
            }
        };

        /**
         * 抽象方法,根据命令的不同,实现不同功能.
         *
         * @param command 命令的第一个单词
         * @param game    当前的游戏对象
         * @return 根据需要返回对应的值
         */
        public abstract Object apply(Command command, Game game);

        /**
         * 用来设置命令类型，暂时没有使用.
         */
        private String type;

        CommandEnum(final String command) {
            this.type = type;
        }
    }

    /**
     * 创建所有房间对象并连接其出口用以构建迷宫.
     */
    private void createRooms() {
        // 创建房间
        Room outside = new Room("outside the main entrance of the university");
        Room theater = new Room("in a lecture theater");
        Room pub = new Room("in the campus pub");
        Room lab = new Room("in a computing lab");
        Room office = new Room("in the computing admin office");

        // 初始化房间出口
        /*outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);*/
        //使用setExitAll一次设置完成
        outside.setAllExit(theater, pub, null, lab);
        theater.setAllExit(null, outside, null, null);
        pub.setAllExit(outside, null, null, null);
        lab.setAllExit(office, null, outside, null);
        office.setAllExit(null, lab, null, null);

        currentRoom = outside;  // start game outside
    }

    /**
     * 游戏主控循环，直到用户输入退出命令后结束整个程序.
     */
    public void play() {
        printWelcome();

        // 进入主命令循环。反复阅读命令并执行它们，直到游戏结束.
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * 向用户输出欢迎信息.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, "
                + "incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * 执行用户输入的游戏指令.
     *
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果执行的是游戏结束指令，则返回true，否则返回false.
     */
    private boolean processCommand(final Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }
        //判断命令的类型
        String commandWord = command.getCommandWord();
        //采用枚举避免过多的if-else语句
        wantToQuit = (Boolean) CommandEnum.valueOf(
                commandWord.toUpperCase()).apply(command, this);
        /*if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }*/
        //修改if else 语句为case语句
        // 否则命令无法识别。
        return wantToQuit;
    }

    // 用户命令的实现:

    /**
     * 执行help指令，在终端打印游戏帮助信息.
     * 此处会输出游戏中用户可以输入的命令列表
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /**
     * 执行go指令，向房间的指定方向出口移动，如果该出口连接了另一个房间，则会进入该房间，
     * 否则打印输出错误提示信息.
     *
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     */
    private void goRoom(final Command command) {
        if (!command.hasSecondWord()) {
            // 如果没有第二个单词，就不知道到哪去
            System.out.println("Go where?");
            return;
        }
        String direction = command.getSecondWord();

        // 试着根据方向离开当前的房间。
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * 执行Quit指令，用户退出游戏。如果用户在命令中输入了其他参数，则进一步询问用户是否真的退出.
     *
     * @param command 待处理的游戏指令，由解析器从用户输入内容生成.
     * @return 如果游戏需要退出则返回true，否则返回false.
     */
    private boolean quit(final Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // 表示想要退出
        }
    }
}
