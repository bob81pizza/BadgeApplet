/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.badge.applet;


import java.applet.Applet;
import java.awt.*;
import java.util.*;
import netscape.javascript.JSObject;

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
	protected static JSObject appletWindowJSObject = null;
        
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
			errors.add("Name parameter not entered - Default used");
		}
		
		if(shape==null){
			shape="RECT";
			errors.add("Shape parameter not entered - Default used");
		}
		
		if(colorString==null){
			colorString="cyan";
			errors.add("Color parameter not entered - Default used");
		}
		
		if(stringY==null){
			y = 30;
			errors.add("Y parameter not entered - Default used");
		}
		else{
			y = Integer.parseInt(stringY);
		}
		
		if(stringX==null){
			x = 100;
			errors.add("X parameter not entered - Default used");
		}
		else{
			x = Integer.parseInt(stringX);
		}
		
		if(ftstyle==null){
			ftstyle = "BOLD";
			errors.add("ftstyle parameter not entered - Default used");
		}
		
		if(ftsizeString==null){
			ftsize = 20;
			errors.add("ftsize parameter not entered - Default used");
		}
		else{
			ftsize = Integer.parseInt(ftsizeString);
		}
		
		if(!bgColorSet){
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
                

                appletWindowJSObject = JSObject.getWindow(this);

		
		if(errors.size()>0){
			String errorString = "";
			for(String s: errors){
				errorString += s+"\n";
			}
                        String[] l = new String[1];
                        l[0] = errorString;
			appletWindowJSObject.call("setError", l);
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
