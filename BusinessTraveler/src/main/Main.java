/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import component.WorldMapPanel;
import tools.BusinessPointFactory;
import javax.swing.JFrame;
import model.BusinessPoint.BusinessPoint;
import model.Environment.Topography.Forest;
import model.Environment.Topography.Mountain;
import model.Environment.Topography.Plain;
import model.Path.Path;
import model.User.User;
import model.WorldMap.WorldMap;
import model.EnumType.EnumTypes.PathType;
import tools.TimeHandler;
import view.MainFrame;

/**
 *
 * @author Yun_c
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {

        BusinessPointFactory bfct = new BusinessPointFactory();
        User u = User.getInstance();
        WorldMap wMap = WorldMap.getInstance();
        TimeHandler tHandler = TimeHandler.getInstance();

        BusinessPoint a = bfct.getBusinessPoint(1, "village A", 300, 300);
        BusinessPoint b = bfct.getBusinessPoint(1, "village B", 450, 530);
        BusinessPoint c = bfct.getBusinessPoint(2, "town C", 700, 200);
        BusinessPoint d = bfct.getBusinessPoint(2, "town D", 1000, 220);
        BusinessPoint e = bfct.getBusinessPoint(3, "city E", 520, 100);
        BusinessPoint f = bfct.getBusinessPoint(3, "city F", 680, 400);

        a.generateTopography(new Plain(new Mountain()));
        b.generateTopography(new Mountain(new Forest()));
        c.generateTopography(new Forest());
        d.generateTopography(new Plain());
        e.generateTopography(new Plain(new Forest()));
        f.generateTopography(new Mountain());

        Path pab = new Path(PathType.DIRT, a, b, 25);
        Path pac = new Path(PathType.DIRT, a, c, 28);
        Path pbc = new Path(PathType.DIRT, b, c, 22);
        Path pcd = new Path(PathType.GRASS, c, d, 17);
        Path pde = new Path(PathType.ROAD, d, e, 13);
        Path pdf = new Path(PathType.ROAD, d, f, 11);

        wMap.addPoint(a);
        wMap.addPoint(b);
        wMap.addPoint(c);
        wMap.addPoint(d);
        wMap.addPoint(e);
        wMap.addPoint(f);
        wMap.addPath(pab);
        wMap.addPath(pac);
        wMap.addPath(pbc);
        wMap.addPath(pcd);
        wMap.addPath(pde);
        wMap.addPath(pdf);

        MainFrame jf = new MainFrame();

        u.setName("Alice");
        u.setMax_energy(80);
        u.setEnergy(60);

        jf.getWorldMapPanel().initWorldMap(wMap.getAllPoints(), wMap.getAllPaths());
        wMap.attach(jf.getNowBusinessPoint());
        wMap.attach(jf.getDestinationPoint());
        wMap.attach(jf.getPathPanel());
        wMap.attach(jf.getDrawedPanel());
        u.attach(jf.getBasicInfoPanel());
        tHandler.attach(jf.getBasicInfoPanel());

        wMap.setArrival(a);
        u.notifyObserver();
//        Thread.sleep(1000);
        jf.setVisible(true);
//        Thread.sleep(1000);
//        u.changeTravelDestination(bf);
//        Thread.sleep(1000);
//        u.travel();

    }
}
