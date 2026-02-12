package com.zengyanyu.system.algo;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Java 下雪动画（带雪花融化效果）
 * 雪花接近底部时逐渐透明、缩小，模拟融化
 */
public class SnowAnimationWithMelting extends JFrame {
    // 雪花列表
    private final List<Snowflake> snowflakes;
    private final Random random;
    // 窗口尺寸
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    // 雪花数量
    private static final int SNOWFLAKE_COUNT = 200;
    // 融化触发高度（雪花落到这个高度开始融化）
    private static final int MELT_HEIGHT = HEIGHT - 100;

    // 雪花实体类（新增融化相关属性）
    private static class Snowflake {
        int x;          // 横坐标
        int y;          // 纵坐标
        int originalSize; // 初始大小（用于融化时的尺寸计算）
        int currentSize;  // 当前大小
        int speed;      // 下落速度
        int drift;      // 左右飘动偏移量
        double driftSpeed; // 飘动速度
        float alpha;    // 透明度（0.0-1.0，0完全透明，1完全不透明）

        public Snowflake(int x, int y, int size, int speed, int drift, double driftSpeed) {
            this.x = x;
            this.y = y;
            this.originalSize = size;
            this.currentSize = size;
            this.speed = speed;
            this.drift = drift;
            this.driftSpeed = driftSpeed;
            this.alpha = 1.0f; // 初始完全不透明
        }

        // 更新雪花位置和融化状态
        public void update() {
            // 下落
            y += speed;
            // 左右飘动
            drift += driftSpeed;
            x += Math.sin(drift) * 2;

            // 融化逻辑：雪花超过融化高度后，逐渐透明、缩小
            if (y > MELT_HEIGHT) {
                // 计算融化进度（0.0-1.0，越接近底部进度越高）
                double meltProgress = (double) (y - MELT_HEIGHT) / (HEIGHT - MELT_HEIGHT);
                // 进度限制在0-1之间，避免超出范围
                meltProgress = Math.min(meltProgress, 1.0);

                // 透明度随进度降低（1.0 → 0.0）
                this.alpha = 1.0f - (float) meltProgress;
                // 尺寸随进度缩小（originalSize → 0）
                this.currentSize = (int) (originalSize * (1.0 - meltProgress));
            } else {
                // 未到融化高度，恢复完全不透明和原始尺寸
                this.alpha = 1.0f;
                this.currentSize = originalSize;
            }

            // 雪花完全融化（落到底部或透明度为0），重新生成
            if (y > HEIGHT || alpha <= 0.0f) {
                reset();
            }
        }

        // 重置雪花到顶部随机位置，恢复初始状态
        private void reset() {
            this.x = new Random().nextInt(WIDTH);
            this.y = -originalSize; // 从窗口顶部外开始下落
            this.alpha = 1.0f;
            this.currentSize = originalSize;
            // 随机重置飘动角度，让效果更自然
            this.drift = new Random().nextInt(360);
        }

        // 绘制雪花（支持透明）
        public void draw(Graphics g) {
            // 转换为Graphics2D以支持透明度设置
            Graphics2D g2d = (Graphics2D) g.create();
            // 设置透明度（Composite）
            AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(ac);

            // 绘制白色雪花（使用当前尺寸）
            g2d.setColor(Color.WHITE);
            if (currentSize > 0) { // 尺寸大于0时才绘制
                g2d.fillOval(x, y, currentSize, currentSize);
            }

            // 释放Graphics2D资源
            g2d.dispose();
        }
    }

    public SnowAnimationWithMelting() {
        snowflakes = new ArrayList<>();
        random = new Random();

        // 窗口初始化
        setTitle("Java 下雪动画（带融化效果）");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // 初始化雪花
        initSnowflakes();

        // 动画线程
        new Thread(() -> {
            while (true) {
                updateSnowflakes();
                repaint();
                try {
                    Thread.sleep(16); // 约60帧/秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // 初始化雪花
    private void initSnowflakes() {
        for (int i = 0; i < SNOWFLAKE_COUNT; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            int size = random.nextInt(4) + 1; // 1-4像素
            int speed = random.nextInt(3) + 1; // 1-3像素/帧
            int drift = random.nextInt(360);
            double driftSpeed = random.nextDouble() * 0.1;
            snowflakes.add(new Snowflake(x, y, size, speed, drift, driftSpeed));
        }
    }

    // 更新所有雪花
    private void updateSnowflakes() {
        for (Snowflake snowflake : snowflakes) {
            snowflake.update();
        }
    }

    // 重写绘图方法
    @Override
    public void paint(Graphics g) {
        // 黑色背景
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        // 绘制所有雪花
        for (Snowflake snowflake : snowflakes) {
            snowflake.draw(g);
        }
    }

    // 程序入口
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SnowAnimationWithMelting().setVisible(true);
        });
    }
}