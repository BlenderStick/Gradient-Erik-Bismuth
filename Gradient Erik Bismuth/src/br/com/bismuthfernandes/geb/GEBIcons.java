package br.com.bismuthfernandes.geb;


import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.plaf.IconUIResource;

public class GEBIcons{
	
	private static String softwareName = "bismuthfernandes";
	private static ClassLoader reference = ClassLoader.getSystemClassLoader();
	private static Icon driver;
	private static Icon directory;
	private static Icon file;
	private static Icon computer;
	private static Icon floppyDriver;
	private static Icon interfaceDetailView;
	private static Icon interfaceListView;
	private static Icon interfaceHomeFolder;
	private static Icon interfaceNewFolder;
	private static Icon interfaceUpFolder;

	public static Icon getFileViewDriver(){
		if(driver==null)
			driver = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/driver.png")));
		return driver;
	}
	public static Icon getFileViewDirectory(){
		if(directory==null)
			directory = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/directory.png")));
		return directory;
	}
	public static Icon getFileViewFile(){
		if(file==null)
			file = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/file.png")));
		return file;
	}
	public static Icon getFileViewComputer(){
		if(computer==null)
			computer = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/computer.png")));
		return computer;
	}
	public static Icon getFileViewFloppyDriver(){
		if(floppyDriver==null)
			floppyDriver = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/floppyDriver.png")));
		return floppyDriver;
	}

	public static Icon getInterfaceHomeFolderIcon(){
		if(interfaceHomeFolder==null)
			interfaceHomeFolder = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/homeFolder.png")));
		return interfaceHomeFolder;
	}
	public static Icon getFileChooserNewFolderIcon(){
		if(interfaceNewFolder==null)
			interfaceNewFolder = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/newFolder.png")));
		return interfaceNewFolder;
	}
	public static Icon getFileChooserUpFolderIcon(){
		if(interfaceUpFolder==null)
			interfaceUpFolder = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/upFolder.png")));
		return interfaceUpFolder;
	}
	public static Icon getInterfaceDetailViewIcon(){
		if(interfaceDetailView==null)
			interfaceDetailView = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/detailView.png")));
		return interfaceDetailView;
	}
	public static Icon getInterfaceListViewIcon(){
		if(interfaceListView==null)
			interfaceListView = new IconUIResource(new ImageIcon(reference.getResource("br/com/"+softwareName+"/geb/listView.png")));
		return interfaceListView;
	}
	
	
}
