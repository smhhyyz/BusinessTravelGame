/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package component;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import javax.swing.JPanel;
import model.BusinessPoint.BusinessPoint;
import model.Path.Path;
import model.WorldMap.WorldMap;
import myinterface.Observer;
import myinterface.Subject;

/**
 *
 * @author Yun_c
 */
public class DrawablePanel extends JPanel implements Observer {

    public DrawablePanel() {
        super();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        WorldMap wMap = WorldMap.getInstance();
        for (Path p : wMap.getAllPaths()) {
            BusinessPointOnMap tempA = this.businessPoints.get(p.getA().getId());
            BusinessPointOnMap tempB = this.businessPoints.get(p.getB().getId());
            int aX = tempA.getX();
            int bX = tempB.getX();
            int aY = tempA.getY();
            int bY = tempB.getY();
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            BasicStroke bs1 = new BasicStroke(5);       // 笔画的轮廓（画笔宽度/线宽为5px）
            g2d.setStroke(bs1);
            g2d.setColor(p.getColor());
            int startX = 0;
            int startY = 0;
            int endX = 0;
            int endY = 0;
            if ((((aY < bY + tempB.getHeight()) && aY > bY) || ((aY + tempA.getHeight() > bY) && aY < bY))
                    && ((aX > bX + tempB.getWidth()) || (aX + tempA.getWidth() < bX))) {

                startX = aX > bX ? bX + tempB.getWidth() : aX + tempA.getWidth();
                endX = aX > bX ? aX : bX;
                startY = aX > bX ? (bY + bY + tempB.getHeight()) / 2 : (aY + aY + tempA.getHeight()) / 2;
                endY = aX > bX ? (aY + aY + tempA.getHeight()) / 2 : (bY + bY + tempB.getHeight()) / 2;

            } else if ((((aX < bX + tempB.getWidth()) && aX > bX) || ((aX + tempA.getWidth() > bX) && aX < bX))
                    && ((aY > bY + tempB.getHeight()) || (aY + tempA.getHeight() < bY))) {

                startX = aY > bY ? (bX + bX + tempB.getWidth()) / 2 : (aX + aX + tempA.getWidth()) / 2;
                endX = aY > bY ? (aX + aX + tempA.getWidth()) / 2 : (bX + bX + tempB.getWidth()) / 2;
                startY = aY > bY ? bY + tempB.getHeight() : aY + tempA.getHeight();
                endY = aY > bY ? aY : bY;

            } else if ((aX + tempA.getWidth() <= bX) && (aY >= bY + tempB.getHeight())) {
                startX = aX + tempA.getWidth();
                startY = aY;
                endX = bX;
                endY = bY + tempB.getHeight();
            } else if ((aX >= bX + tempB.getWidth()) && (aY >= bY + tempB.getHeight())) {
                startX = aX;
                startY = aY;
                endX = bX + tempB.getWidth();
                endY = bY + tempB.getHeight();
            } else if ((aX >= bX + tempB.getWidth()) && (aY + tempA.getHeight() <= bY)) {
                startX = aX;
                startY = aY + tempA.getHeight();
                endX = bX + tempB.getWidth();
                endY = bY;
            } else {
                startX = aX + tempA.getWidth();
                startY = aY + tempA.getHeight();
                endX = bX;
                endY = bY;
            }
            g2d.drawLine(startX, startY, endX, endY);

        }
    }

    private Map<Integer, BusinessPointOnMap> businessPoints;

    public Collection<BusinessPointOnMap> getAllPoints() {
        return this.businessPoints.values();
    }

    public void initWorldMap(BusinessPoint[] bps, Path[] paths) {
        businessPoints = new HashMap<Integer, BusinessPointOnMap>();
        WorldMap wMap = WorldMap.getInstance();
        int bpWidth = 50;
        int bpHeight = 50;
        int maxX = this.getParent().getWidth() - bpWidth;
        int maxY = this.getParent().getHeight() - bpHeight;
        Random rnd = new Random();
        for (int i = 0; i < bps.length; i++) {
            BusinessPointOnMap temp = new BusinessPointOnMap(bps[i]);
            this.businessPoints.put(temp.getId(), temp);
            this.add(temp);
            wMap.attach(temp);

            if (bps[i].getX() > 0 && bps[i].getY() > 0) {
                temp.setBounds(bps[i].getX(), bps[i].getY(), bpWidth, bpHeight);
                continue;
            }

            if (i == 0) {
                temp.setBounds(maxX / 2, maxY / 2, bpWidth, bpHeight);
            } else {
                boolean seperate = false;
                int bp_x = 0;
                int bp_y = 0;
                while (!seperate) {
                    boolean jump = false;
                    bp_x = rnd.nextInt(maxX);
                    bp_y = rnd.nextInt(maxY);
                    for (BusinessPointOnMap bm : this.businessPoints.values()) {
                        if (bp_x > bm.getX() + bpWidth + 50
                                || bp_x + bpWidth < bm.getX() - 50
                                || bp_y > bm.getY() + bpHeight + 50
                                || bp_y + bpHeight < bm.getY() - 50) {
                        } else {
                            jump = true;
                            break;
                        }
                    }
                    if (!jump) {
                        seperate = true;
                    }
                }
                temp.setBounds(bp_x, bp_y, bpWidth, bpHeight);
            }
        }

    }

    @Override
    public void update(Subject s) {
       if(s instanceof WorldMap){
           this.repaint();
           this.revalidate();
       }
    }
}
