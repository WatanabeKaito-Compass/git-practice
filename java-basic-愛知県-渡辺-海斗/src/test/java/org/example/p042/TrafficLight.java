package org.example.p042;

/**
 * 状態を表す列挙型
 */
public enum TrafficLight {
    RED,
    GREEN,
    YELLOW;
    
    public TrafficLight next() {
        return switch (this) {
            case RED -> GREEN;
            case GREEN -> YELLOW;
            case YELLOW -> RED;
        };
    }
}

