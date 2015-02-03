/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.badge.applet;


import java.applet.Applet;
import java.awt.*;
import java.util.*;

import javax.swing.JOptionPane;

public class BadgeApplet extends Applet{
	
	String name;
	String shape;
	String colorString;
	String bgcolor;
	String stringX;
	String stringY;
	String ftstyle;
	String ftsizeString;
	boolean bgColorSet = true;
	int ftsize, x, y, nameX, nameY;
	Map<String, Color> colors = new HashMap();
	Map<String, Integer> fonts = new HashMap();
	ArrayList<String> errors = new ArrayList();
	Font f;
	
	public void init(){
		
		colors.put("blue", Color.blue);
		colors.put("black", Color.black);
		colors.put("cyan", Color.cyan);
		colors.put("darkGray", Color.darkGray);
		colors.put("gray", Color.gray);
		colors.put("green", Color.green);
		colors.put("lightGray", Color.lightGray);
		colors.put("magenta", Color.magenta);
		colors.put("orange", Color.orange);
		colors.put("pink", Color.pink);
		colors.put("red", Color.red);
		colors.put("white", Color.white);
		colors.put("yellow", Color.yellow);
		
		fonts.put("REGULAR", Font.PLAIN);
		fonts.put("BOLD", Font.BOLD);
		fonts.put("ITALIC", Font.ITALIC);

		name = this.getParameter("NAME");
		
		shape = this.getParameter("SHAPE");

		colorString = this.getParameter("COLOR");

		stringX = (this.getParameter("X"));
		stringY = (this.getParameter("Y"));
		
		ftstyle = this.getParameter("FTSTYLE");
		
		ftsizeString = (this.getParameter("FTSIZE"));
		
		bgcolor = this.getParameter("BGCOLOR");
		
		if(colors.containsKey(bgcolor)){
			setBackground((Color)colors.get(bgcolor));
		}
		else{
			setBackground(Color.red);
			showStatus("background color not recognized");
			bgColorSet = false;
		}
		if(name==null){
			name="Hello";
			showStatus("Name parameter not entered");
			errors.add("Name parameter not entered");
		}
		
		if(shape==null){
			shape="RECT";
			showStatus("Shape parameter not entered");
			errors.add("Shape parameter not entered");
		}
		
		if(colorString==null){
			colorString="cyan";
			showStatus("Color parameter not entered");
			errors.add("Color parameter not entered");
		}
		
		if(stringY==null){
			y = 30;
			showStatus("Y parameter not entered");
			errors.add("Y parameter not entered");
		}
		else{
			y = Integer.parseInt(stringY);
		}
		
		if(stringX==null){
			x = 100;
			showStatus("X parameter not entered");
			errors.add("X parameter not entered");
		}
		else{
			x = Integer.parseInt(stringX);
		}
		
		if(ftstyle==null){
			ftstyle = "BOLD";
			showStatus("ftstyle parameter not entered");
			errors.add("ftstyle parameter not entered");
		}
		
		if(ftsizeString==null){
			ftsize = 20;
			showStatus("ftsize parameter not entered");
			errors.add("ftsize parameter not entered");
		}
		else{
			ftsize = Integer.parseInt(ftsizeString);
		}
		
		if(!bgColorSet){
			showStatus("background color not entered");
			errors.add("bgcolor parameter not entered or not valid");
		}
		
		
	}

	public void paint(Graphics g){
		
		
		
		g.setColor((Color)colors.get(colorString));
		int xoffset = 125;
		int yoffset = 50;
		

		Dimension d = getSize();
		
		if(ftstyle.equals("BOLD ITALIC"))
			f=new Font("Arial", Font.BOLD + Font.ITALIC, ftsize);
		else{
			f = new Font("Serif", fonts.get(ftstyle), ftsize);
		}
		
		g.setFont(f);
		
		FontMetrics fm = g.getFontMetrics();
		nameX = d.width/2 - fm.stringWidth(name)/2;
		nameY = d.height/2 + fm.getDescent();
		
		g.drawString(name, nameX, nameY);
		
		if(fm.stringWidth(name)>x || (fm.getAscent())>y){
			errors.add("Text is too big!!");
		}
		
		if(errors.size()>0){
			String errorString = "";
			for(String s: errors){
				errorString += s+"\n";
			}
			JOptionPane.showMessageDialog(null, errorString);
		}

		
		try{
			if(shape.equals("OVAL")){ 
				g.drawOval(d.width/2-(x/2),d.height/2-(y/2),x,y);
			}
			
			if(shape.equals("RECT")){
				g.drawRect(d.width/2-(x/2),d.height/2-(y/2),x,y);
			}
			
			if(shape.equals("ROUNDRECT")){
				g.drawRoundRect(d.width/2-(x/2), d.height/2-(y/2), x, y, 25, 50);
			}
		}
		catch(Exception e){
			System.out.println("broken");
		}
		



	}

}
