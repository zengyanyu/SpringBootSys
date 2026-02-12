package com.zengyanyu.system.algo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Java 下雪动画程序
 * 基于 Swing 实现，包含雪花的随机生成、下落、飘动效果
 */
public class SnowAnimation extends JFrame {
    // 雪花列表，存储所有雪花对象
    private final List<Snowflake> snowflakes;
    // 随机数生成器
    private final Random random;
    // 窗口宽度和高度
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    // 雪花数量
    private static final int SNOWFLAKE_COUNT = 200;

    // 雪花实体类，封装雪花的属性和行为
    private static class Snowflake {
        int x;          // 横坐标
        int y;          // 纵坐标
        int size;       // 雪花大小
        int speed;      // 下落速度
        int drift;      // 左右飘动偏移量
        double driftSpeed; // 飘动速度

        public Snowflake(int x, int y, int size, int speed, int drift, double driftSpeed) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.speed = speed;
            this.drift = drift;
            this.driftSpeed = driftSpeed;
        }

        // 更新雪花位置（下落+飘动）
        public void update() {
            // 下落
            y += speed;
            // 左右飘动（模拟风的效果）
            drift += driftSpeed;
            x += Math.sin(drift) * 2;

            // 雪花落到窗口底部后，重新从顶部生成
            if (y > HEIGHT) {
                y = -size;
                x = new Random().nextInt(WIDTH);
            }
        }

        // 绘制雪花
        public void draw(Graphics g) {
            g.setColor(Color.WHITE);
            // 绘制圆形雪花（也可以改成六边形，效果更逼真）
            g.fillOval(x, y, size, size);
        }
    }

    public SnowAnimation() {
        // 初始化雪花列表和随机数
        snowflakes = new ArrayList<>();
        random = new Random();

        // 初始化窗口
        setTitle("Java 下雪动画");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 窗口居中
        setResizable(false);

        // 生成初始雪花
        initSnowflakes();

        // 启动动画线程（控制帧率）
        new Thread(() -> {
            while (true) {
                // 更新所有雪花位置
                updateSnowflakes();
                // 重绘窗口
                repaint();
                try {
                    // 控制帧率（约60帧/秒）
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 初始化雪花
    private void initSnowflakes() {
        for (int i = 0; i < SNOWFLAKE_COUNT; i++) {
            int x = random.nextInt(WIDTH);          // 随机横坐标
            int y = random.nextInt(HEIGHT);         // 随机纵坐标
            int size = random.nextInt(4) + 1;       // 雪花大小（1-4像素）
            int speed = random.nextInt(3) + 1;      // 下落速度（1-3像素/帧）
            int drift = random.nextInt(360);        // 初始飘动角度
            double driftSpeed = random.nextDouble() * 0.1; // 飘动速度
            snowflakes.add(new Snowflake(x, y, size, speed, drift, driftSpeed));
        }
    }

    // 更新所有雪花位置
    private void updateSnowflakes() {
        for (Snowflake snowflake : snowflakes) {
            snowflake.update();
        }
    }

    // 重写绘图方法，绘制所有雪花
    @Override
    public void paint(Graphics g) {
        // 先绘制黑色背景（模拟黑夜）
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 绘制所有雪花
        for (Snowflake snowflake : snowflakes) {
            snowflake.draw(g);
        }
    }

    // 程序入口
    public static void main(String[] args) {
        // Swing 组件需要在事件调度线程中运行
        SwingUtilities.invokeLater(() -> {
            new SnowAnimation().setVisible(true);
        });
    }
}
