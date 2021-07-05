package cn.edu.whut.sept.zuul;

import java.util.Set;
import java.util.HashMap;

/**
 * 这个类是“World of Zuul”应用程序的房间类.
 * <p>
 * 初始化一个Room对象需要一个对当前房间的描述，并通过setExit（）设置出口方向和对应的房间，Room对象还可以根据其它类的需求获取
 * 相应的长短描述并返回对应的字符串或者根据对应的方向获取对应的房间
 *
 * @author yzh
 * @version 2021.06.28
 */
public class Room {
    /**
     * 房间的描述.
     */
    private String description;
    /**
     * 房间的出口的map.
     */
    private HashMap<String, Room> exits;

    /**
     * 创建房间并初始化内部数据包括描述和出口，出口由HashMap初始化.
     *
     * @param des 初始化时对房间的描述.
     */
    public Room(final String des) {
        this.description = des;
        exits = new HashMap<String, Room>();
    }

    /**
     * 设置一个房间的出口，包括方向和出去后到达的地方.
     *
     * @param direction 想要去的方向,由Game类自动加入.
     * @param neighbor  去的方向的出口房间，由Game类自动加入.
     */
    public void setExit(final String direction, final Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * 一次设置房间的所有出口.
     *
     * @param eastRoom  东方向房间
     * @param westRoom  西方向房间
     * @param northRoom 北方向房间
     * @param southRoom 南方向房间
     */
    public void setAllExit(final Room eastRoom, final Room westRoom,
                           final Room northRoom, final Room southRoom) {
        exits.put("east", eastRoom);
        exits.put("south", southRoom);
        exits.put("west", westRoom);
        exits.put("north", northRoom);
    }

    /**
     * 获取一个简短的描述.
     *
     * @return 返回当前房间的描述的字符串
     */
    public String getShortDescription() {
        return description;
    }

    /**
     * 获取一个详细的描述.
     *
     * @return 返回如"You are in xxx.\n Exits: xx xx.（xxx为当前房间的描述，xx为存在的出口方向）"的字符串
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * 获取房间存在的出口方向，通过循环把每一个出口加入到字符串.
     *
     * @return 返回当前房间所有出口的字符串
     */
    private String getExitString() {
        String returnString = "Exits:";

        //获取exits这个map的所有键值
        Set<String> keys = exits.keySet();

        //循环将出口方向加入到字符串
        for (String exit : keys) {
            if (null != exits.get(exit)) {
                returnString += " " + exit;
            }
        }
        return returnString;
    }

    /**
     * 找到输入方向出口对应的房间.
     *
     * @param direction 用户输入的想要去的方向
     * @return 该方向对应的Room对象
     */
    public Room getExit(final String direction) {
        return exits.get(direction); //返回该方向对应的Room
    }
}


