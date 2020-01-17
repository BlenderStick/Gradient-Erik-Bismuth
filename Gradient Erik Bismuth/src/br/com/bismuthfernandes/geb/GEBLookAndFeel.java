package br.com.bismuthfernandes.geb;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.basic.BasicLookAndFeel;
import javax.swing.text.DefaultEditorKit;

import sun.swing.SwingLazyValue;


/**
 * Gradient Erik Bismuth, Look And Feel do Erik
 * @author Erik Fernandes
 *
 */
public class GEBLookAndFeel extends BasicLookAndFeel{
	
	public boolean getSupportsWindowDecorations() {return true;}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6744325784153963755L;
	
	public static String softwareName = "bismuthfernandes";
	
	protected void initClassDefaults(UIDefaults table) {
	    super.initClassDefaults(table);
	    String pName = "br.com."+softwareName+".geb.";

	    Object[] uiDefaults;// = {
//	              "ButtonUI", ScePackageName + "SceButtonUI",
//	                "MenuUI", ScePackageName + "SceMenuUI",
//	         "ProgressBarUI", ScePackageName + "SceProgressBarUI",
//	           "ScrollBarUI", ScePackageName + "SceScrollBarUI",
//	           "SplitPaneUI", ScePackageName + "SceSplitPaneUI",
//	          "TabbedPaneUI", ScePackageName + "SceTabbedPaneUI",
//	         "TableHeaderUI", ScePackageName + "SceTableHeaderUI",
//	        "ToggleButtonUI", ScePackageName + "SceToggleButtonUI",
//	             "ToolBarUI", ScePackageName + "SceToolBarUI",
//	    };
	    uiDefaults = new Object[]{	
	    		"ButtonUI", pName + "GEBButtonUI",
	    		"MenuUI", pName + "GEBMenuUI",
	    		"MenuItemUI", pName + "GEBMenuItemUI",
	    		
	    		"ScrollBarUI", pName + "GEBScrollBarUI",
	    		"SpinnerUI", pName + "GEBSpinnerUI", 
	    		
	    		
	    		
	    		
	    		
	    		"PanelUI", pName + "GEBPanelUI",
	    		"CheckBoxUI", pName + "GEBCheckBoxUI",
	    		"ComboBoxUI", pName + "GEBComboBoxUI",
	    		"ToggleButtonUI", pName + "GEBToggleButtonUI",
//	    		"PopupMenuUI", pName + "GEBPopupMenuUI",
	    		"FileChooserUI", pName + "GEBFileChooserUI",
	    		"LabelUI", pName + "GEBLabelUI",
	    		"SliderUI", pName + "GEBSliderUI",
	    };
	    table.putDefaults(uiDefaults);
	}
	protected void initSystemColorDefaults(UIDefaults table){super.initSystemColorDefaults(table);}
	protected void initComponentDefaults(UIDefaults table){
		super.initComponentDefaults(table);
	    String pathName = "br.com."+softwareName+".geb";
		
		
		
//		table.addResourceBundle("Gea");
		int roundRectangleDefault = 10;
		float borderSize = 1f;
		FontUIResource font = new FontUIResource("Arial", Font.BOLD, 12);
		
		Object[] button;
		Object [] arrowButton;
		Object [] menuItem;
		Object[] scrollBar;
		Object[] toggleButton;
		Object[] popupMenu;
		Object[] textDefaults;
			Object fieldInputMap;
			Object passwordInputMap;
			Object multilineInputMap;
		Object[] comboBox;
		Object[] label;
		Object[] spinner;
		Object[] slider;
		
		
		Object[] fileChooserDefaults;
		Object[] fileView;
		
		button = new Object[] {
				"Button.normalTop", new ColorUIResource(180,180,180),
				"Button.normalBottom", new ColorUIResource(30,30,30),
				"Button.pressTop", new ColorUIResource(160,160,160),
				"Button.pressBottom", new ColorUIResource(0,0,0),
				"Button.activeTop", new ColorUIResource(200,200,200),
				"Button.activeBottom", new ColorUIResource(50,50,50),
				"Button.disableTop", new ColorUIResource(100,100,100),
				"Button.disableBottom", new ColorUIResource(50,50,50),
				"Button.textNormal", new ColorUIResource(0, 0, 0),
				"Button.textSelect", new ColorUIResource(50, 50, 50),
				"Button.textPress", new ColorUIResource(255, 255, 255),
				"Button.focus", new ColorUIResource(0, 0, 0),
				"Button.focusable", Boolean.FALSE, 
				"Button.font", font,
				"Button.defaultButtonFollowsFocus", Boolean.FALSE,
				"Button.borderColor", new ColorUIResource(Color.BLACK),
				"Button.borderSize", Float.valueOf(borderSize),
	            "Button.rollover", Boolean.TRUE,
				"Button.border", GEBBorders.getButtonBorder(),
				"Button.borderRound", Integer.valueOf(roundRectangleDefault),
	            "Button.focusInputMap", new UIDefaults.LazyInputMap(new Object[] {
	            		"SPACE", "pressed",
	            		"released SPACE", "released"
	            		}),
				
				
		};
		arrowButton = new Object[]{
				"ArrowButton.normalTop", new ColorUIResource(180,180,180),
				"ArrowButton.normalBottom", new ColorUIResource(30,30,30),
				"ArrowButton.pressTop", new ColorUIResource(160,160,160),
				"ArrowButton.pressBottom", new ColorUIResource(0,0,0),
				"ArrowButton.activeTop", new ColorUIResource(200,200,200),
				"ArrowButton.activeBottom", new ColorUIResource(50,50,50),
				"ArrowButton.disableTop", new ColorUIResource(100,100,100),
				"ArrowButton.disableBottom", new ColorUIResource(50,50,50),
				"ArrowButton.borderRound", Integer.valueOf(roundRectangleDefault),
				"ArrowButton.font", font,
		};
		
		menuItem = new Object[]{
				"MenuItem.background", new ColorUIResource(100, 100, 100),
				"MenuItem.foreground", new ColorUIResource(0, 0, 0),
				"MenuItem.selectionBackground", new ColorUIResource(50, 50, 50),
				"MenuItem.selectionForeground", new ColorUIResource(255, 255, 255),
				"MenuItem.disableBackground", new ColorUIResource(125, 125, 125),
				"MenuItem.disableForeground", new ColorUIResource(175, 175, 175),
				"MenuItem.font", font,
		};

		scrollBar = new Object[] {
				"ScrollBar.opaque", Boolean.FALSE,
				"ScrollBar.direction", Boolean.TRUE,
				"ScrollBar.directionColor", new ColorUIResource(0, 0, 0),
				"ScrollBar.thumbNormalTop", new ColorUIResource(120, 120, 120),
				"ScrollBar.thumbNormalBottom", new ColorUIResource(50, 50, 50),
				"ScrollBar.thumbPressTop", new ColorUIResource(100, 100, 100),
				"ScrollBar.thumbPressBottom", new ColorUIResource(40, 40, 40),
				"ScrollBar.thumbActiveTop", new ColorUIResource(150, 150, 150),
				"ScrollBar.thumbActiveBottom", new ColorUIResource(75, 75, 75),
				"ScrollBar.thumbDisableTop", new ColorUIResource(100, 100, 100),
				"ScrollBar.thumbDisableBottom", new ColorUIResource(50, 50, 50),
				
		};
		

		Object[] checkBox = {
	            "CheckBox.opaque", Boolean.FALSE,
				"CheckBox.normalTop", new ColorUIResource(100, 100, 100),
				"CheckBox.normalBottom", new ColorUIResource(30, 30, 30),
				"CheckBox.pressTop", new ColorUIResource(050, 050, 050),
				"CheckBox.pressBottom", new ColorUIResource(30, 30, 30),
				"CheckBox.activeTop", new ColorUIResource(150, 150, 150),
				"CheckBox.activeBottom", new ColorUIResource(50, 50, 50),
				"CheckBox.disableTop", new ColorUIResource(100, 100, 100),
				"CheckBox.disableBottom", new ColorUIResource(50, 50, 50),
				"CheckBox.textNormal", new ColorUIResource(0, 0, 0),
				"CheckBox.textSelect", new ColorUIResource(50, 50, 50),
				"CheckBox.textPress", new ColorUIResource(255, 255, 255),
				"CheckBox.markColor", new ColorUIResource(255, 255, 255),
				"CheckBox.focus", new ColorUIResource(0, 0, 0),
				"CheckBox.focusable", Boolean.FALSE, 
				"CheckBox.font", font,
				"CheckBox.defaultButtonFollowsFocus", Boolean.FALSE,
				"CheckBox.borderColor", new ColorUIResource(Color.BLACK),
				"CheckBox.borderSize", Float.valueOf(borderSize),
				"CheckBox.borderRound", Integer.valueOf(roundRectangleDefault),
	            "CheckBox.rollover", Boolean.TRUE,
				"CheckBox.border", GEBBorders.getCheckBoxBorder(),
	            "CheckBox.focusInputMap", new UIDefaults.LazyInputMap(new Object[] {
	            		"SPACE", "pressed",
	            		"released SPACE", "released"
	            		}),
				
				
		};
		
//		Object[] comboBox = {
//			"ComboBox.ancestorInputMap", new UIDefaults.LazyInputMap(new Object[] {
//					"ESCAPE", "hidePopup",
//					"PAGE_UP", "pageUpPassThrough",
//					"PAGE_DOWN", "pageDownPassThrough",
//					"HOME", "homePassThrough",
//					"END", "endPassThrough",
//					"DOWN", "selectNext",
//					"KP_DOWN", "selectNext",
//					"alt DOWN", "togglePopup",
//					"alt KP_DOWN", "togglePopup",
//					"alt UP", "togglePopup",
//					"alt KP_UP", "togglePopup",
//					"SPACE", "spacePopup",
//					"ENTER", "enterPressed",
//					"UP", "selectPrevious",
//					"KP_UP", "selectPrevious"
//					}),
//		};
		
		toggleButton = new Object[] {
			"ToggleButton.normalTop", new ColorUIResource(180,180,180),
			"ToggleButton.normalBottom", new ColorUIResource(30,30,30),
			"ToggleButton.pressTop", new ColorUIResource(160,160,160),
			"ToggleButton.pressBottom", new ColorUIResource(0,0,0),
			"ToggleButton.activeTop", new ColorUIResource(200,200,200),
			"ToggleButton.activeBottom", new ColorUIResource(50,50,50),
			"ToggleButton.selectTop", new ColorUIResource(50, 50, 50),
			"ToggleButton.selectBottom", new ColorUIResource(25, 25, 25),
			"ToggleButton.selectActiveTop", new ColorUIResource(100, 100, 100),
			"ToggleButton.selectActiveBottom", new ColorUIResource(35, 35, 35),
			"ToggleButton.disableTop", new ColorUIResource(100,100,100),
			"ToggleButton.disableBottom", new ColorUIResource(50,50,50),
			"ToggleButton.textNormal", new ColorUIResource(0, 0, 0),
			"ToggleButton.textSelect", new ColorUIResource(50, 50, 50),
			"ToggleButton.textPress", new ColorUIResource(255, 255, 255),
			"ToggleButton.focus", new ColorUIResource(0, 0, 0),
			"ToggleButton.focusable", Boolean.FALSE,
			"ToggleButton.font", font,
			"ToggleButton.defaultButtonFollowsFocus", Boolean.FALSE,
			"ToggleButton.borderColor", new ColorUIResource(Color.BLACK),
			"ToggleButton.borderSize", Float.valueOf(borderSize),
			"ToggleButton.borderRound", Integer.valueOf(roundRectangleDefault),
			"ToggleButton.rollover", Boolean.TRUE,
			"ToggleButton.border", GEBBorders.getButtonBorder(),
			"ToggleButton.focusInputMap", new UIDefaults.LazyInputMap(new Object[] {
					"SPACE", "pressed",
					"released SPACE", "released"
					}),
		};
		
		Object popupMenuBorder = 
			new SwingLazyValue(
					pathName+".GEBBorders", 
					"getPopupMenuBorder");
		
		popupMenu = new Object[] {
			"PopupMenu.border", popupMenuBorder,	
		};
		
		fieldInputMap = new UIDefaults.LazyInputMap(new Object[] {
                   "ctrl C", DefaultEditorKit.copyAction,
                   "ctrl V", DefaultEditorKit.pasteAction,
                   "ctrl X", DefaultEditorKit.cutAction,
                     "COPY", DefaultEditorKit.copyAction,
                    "PASTE", DefaultEditorKit.pasteAction,
                      "CUT", DefaultEditorKit.cutAction,
           "control INSERT", DefaultEditorKit.copyAction,
             "shift INSERT", DefaultEditorKit.pasteAction,
             "shift DELETE", DefaultEditorKit.cutAction,
               "shift LEFT", DefaultEditorKit.selectionBackwardAction,
            "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
              "shift RIGHT", DefaultEditorKit.selectionForwardAction,
           "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
                "ctrl LEFT", DefaultEditorKit.previousWordAction,
             "ctrl KP_LEFT", DefaultEditorKit.previousWordAction,
               "ctrl RIGHT", DefaultEditorKit.nextWordAction,
            "ctrl KP_RIGHT", DefaultEditorKit.nextWordAction,
          "ctrl shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
       "ctrl shift KP_LEFT", DefaultEditorKit.selectionPreviousWordAction,
         "ctrl shift RIGHT", DefaultEditorKit.selectionNextWordAction,
      "ctrl shift KP_RIGHT", DefaultEditorKit.selectionNextWordAction,
                   "ctrl A", DefaultEditorKit.selectAllAction,
                     "HOME", DefaultEditorKit.beginLineAction,
                      "END", DefaultEditorKit.endLineAction,
               "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                "shift END", DefaultEditorKit.selectionEndLineAction,
               "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
         "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                   "ctrl H", DefaultEditorKit.deletePrevCharAction,
                   "DELETE", DefaultEditorKit.deleteNextCharAction,
              "ctrl DELETE", DefaultEditorKit.deleteNextWordAction,
          "ctrl BACK_SPACE", DefaultEditorKit.deletePrevWordAction,
                    "RIGHT", DefaultEditorKit.forwardAction,
                     "LEFT", DefaultEditorKit.backwardAction,
                 "KP_RIGHT", DefaultEditorKit.forwardAction,
                  "KP_LEFT", DefaultEditorKit.backwardAction,
                    "ENTER", JTextField.notifyAction,
          "ctrl BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
           "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
		});

		passwordInputMap = new UIDefaults.LazyInputMap(new Object[] {
                   "ctrl C", DefaultEditorKit.copyAction,
                   "ctrl V", DefaultEditorKit.pasteAction,
                   "ctrl X", DefaultEditorKit.cutAction,
                     "COPY", DefaultEditorKit.copyAction,
                    "PASTE", DefaultEditorKit.pasteAction,
                      "CUT", DefaultEditorKit.cutAction,
           "control INSERT", DefaultEditorKit.copyAction,
             "shift INSERT", DefaultEditorKit.pasteAction,
             "shift DELETE", DefaultEditorKit.cutAction,
               "shift LEFT", DefaultEditorKit.selectionBackwardAction,
            "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
              "shift RIGHT", DefaultEditorKit.selectionForwardAction,
           "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
                "ctrl LEFT", DefaultEditorKit.beginLineAction,
             "ctrl KP_LEFT", DefaultEditorKit.beginLineAction,
               "ctrl RIGHT", DefaultEditorKit.endLineAction,
            "ctrl KP_RIGHT", DefaultEditorKit.endLineAction,
          "ctrl shift LEFT", DefaultEditorKit.selectionBeginLineAction,
       "ctrl shift KP_LEFT", DefaultEditorKit.selectionBeginLineAction,
         "ctrl shift RIGHT", DefaultEditorKit.selectionEndLineAction,
      "ctrl shift KP_RIGHT", DefaultEditorKit.selectionEndLineAction,
                   "ctrl A", DefaultEditorKit.selectAllAction,
                     "HOME", DefaultEditorKit.beginLineAction,
                      "END", DefaultEditorKit.endLineAction,
               "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                "shift END", DefaultEditorKit.selectionEndLineAction,
               "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
         "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                   "ctrl H", DefaultEditorKit.deletePrevCharAction,
                   "DELETE", DefaultEditorKit.deleteNextCharAction,
                    "RIGHT", DefaultEditorKit.forwardAction,
                     "LEFT", DefaultEditorKit.backwardAction,
                 "KP_RIGHT", DefaultEditorKit.forwardAction,
                  "KP_LEFT", DefaultEditorKit.backwardAction,
                    "ENTER", JTextField.notifyAction,
          "ctrl BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
           "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
	});

		multilineInputMap = new UIDefaults.LazyInputMap(new Object[] {
                   "ctrl C", DefaultEditorKit.copyAction,
                   "ctrl V", DefaultEditorKit.pasteAction,
                   "ctrl X", DefaultEditorKit.cutAction,
                     "COPY", DefaultEditorKit.copyAction,
                    "PASTE", DefaultEditorKit.pasteAction,
                      "CUT", DefaultEditorKit.cutAction,
           "control INSERT", DefaultEditorKit.copyAction,
             "shift INSERT", DefaultEditorKit.pasteAction,
             "shift DELETE", DefaultEditorKit.cutAction,
               "shift LEFT", DefaultEditorKit.selectionBackwardAction,
            "shift KP_LEFT", DefaultEditorKit.selectionBackwardAction,
              "shift RIGHT", DefaultEditorKit.selectionForwardAction,
           "shift KP_RIGHT", DefaultEditorKit.selectionForwardAction,
                "ctrl LEFT", DefaultEditorKit.previousWordAction,
             "ctrl KP_LEFT", DefaultEditorKit.previousWordAction,
               "ctrl RIGHT", DefaultEditorKit.nextWordAction,
            "ctrl KP_RIGHT", DefaultEditorKit.nextWordAction,
          "ctrl shift LEFT", DefaultEditorKit.selectionPreviousWordAction,
       "ctrl shift KP_LEFT", DefaultEditorKit.selectionPreviousWordAction,
         "ctrl shift RIGHT", DefaultEditorKit.selectionNextWordAction,
      "ctrl shift KP_RIGHT", DefaultEditorKit.selectionNextWordAction,
                   "ctrl A", DefaultEditorKit.selectAllAction,
                     "HOME", DefaultEditorKit.beginLineAction,
                      "END", DefaultEditorKit.endLineAction,
               "shift HOME", DefaultEditorKit.selectionBeginLineAction,
                "shift END", DefaultEditorKit.selectionEndLineAction,

                       "UP", DefaultEditorKit.upAction,
                    "KP_UP", DefaultEditorKit.upAction,
                     "DOWN", DefaultEditorKit.downAction,
                  "KP_DOWN", DefaultEditorKit.downAction,
                  "PAGE_UP", DefaultEditorKit.pageUpAction,
                "PAGE_DOWN", DefaultEditorKit.pageDownAction,
            "shift PAGE_UP", "selection-page-up",
          "shift PAGE_DOWN", "selection-page-down",
       "ctrl shift PAGE_UP", "selection-page-left",
     "ctrl shift PAGE_DOWN", "selection-page-right",
                 "shift UP", DefaultEditorKit.selectionUpAction,
              "shift KP_UP", DefaultEditorKit.selectionUpAction,
               "shift DOWN", DefaultEditorKit.selectionDownAction,
            "shift KP_DOWN", DefaultEditorKit.selectionDownAction,
                    "ENTER", DefaultEditorKit.insertBreakAction,
               "BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
         "shift BACK_SPACE", DefaultEditorKit.deletePrevCharAction,
                   "ctrl H", DefaultEditorKit.deletePrevCharAction,
                   "DELETE", DefaultEditorKit.deleteNextCharAction,
              "ctrl DELETE", DefaultEditorKit.deleteNextWordAction,
          "ctrl BACK_SPACE", DefaultEditorKit.deletePrevWordAction,
                    "RIGHT", DefaultEditorKit.forwardAction,
                     "LEFT", DefaultEditorKit.backwardAction,
                 "KP_RIGHT", DefaultEditorKit.forwardAction,
                  "KP_LEFT", DefaultEditorKit.backwardAction,
                      "TAB", DefaultEditorKit.insertTabAction,
          "ctrl BACK_SLASH", "unselect"/*DefaultEditorKit.unselectAction*/,
                "ctrl HOME", DefaultEditorKit.beginAction,
                 "ctrl END", DefaultEditorKit.endAction,
          "ctrl shift HOME", DefaultEditorKit.selectionBeginAction,
           "ctrl shift END", DefaultEditorKit.selectionEndAction,
                   "ctrl T", "next-link-action",
             "ctrl shift T", "previous-link-action",
               "ctrl SPACE", "activate-link-action",
           "control shift O", "toggle-componentOrientation"/*DefaultEditorKit.toggleComponentOrientation*/
		});
		
		textDefaults = new Object[] {
				"TextField.focusInputMap", fieldInputMap,
				"PasswordField.focusInputMap", passwordInputMap,
				"TextArea.focusInputMap", multilineInputMap,
				"TextPane.focusInputMap", multilineInputMap,
				"EditorPane.focusInputMap", multilineInputMap,
				
		};
		
		fileChooserDefaults = new Object[] {
				"FileChooser.lookInLabelText", "",
				"FileChooser.saveInLabelText", "",
				
				"FileChooser.fileNameLabelText", "", 
				"FileChooser.folderNameLabelText", "",
				
				"FileChooser.filesOfTypeLabelText", "",

				"FileChooser.upFolderToolTipText", "",
				"FileChooser.upFolderAccessibleName", "",

				"FileChooser.homeFolderToolTipText", "",
				"FileChooser.homeFolderAccessibleName", "",

				"FileChooser.newFolderToolTipText", "",
				"FileChooser.newFolderAccessibleName", "",

				"FileChooser.listViewButtonToolTipText", "",
				"FileChooser.listViewButtonAccessibleName", "",
				
				"FileChooser.detailsViewButtonToolTipText", "",
				"FileChooser.detailsViewButtonAccessibleName", "",
				
				"FileChooser.readOnly", Boolean.FALSE,
				
				"FileChooser.detailsViewIcon", new SwingLazyStatic(pathName+".GEBIcons", "getInterfaceDetailViewIcon"),
				"FileChooser.listViewIcon", new SwingLazyStatic(pathName+".GEBIcons", "getInterfaceListViewIcon"),
				
				"FileChooser.homeFolderIcon", new SwingLazyStatic(pathName+".GEBIcons", "getInterfaceHomeFolderIcon"),
				"FileChooser.newFolderIcon", new SwingLazyStatic(pathName+".GEBIcons", "getFileChooserNewFolderIcon"),
				"FileChooser.upFolderIcon", new SwingLazyStatic(pathName+".GEBIcons", "getFileChooserUpFolderIcon"),
				
				
		};
		
		
		fileView = new Object[]{
				"FileView.directoryIcon", new SwingLazyStatic(pathName+".GEBIcons", "getFileViewDirectory"),
				"FileView.fileIcon", new SwingLazyStatic(pathName+".GEBIcons", "getFileViewFile"),
				"FileView.computerIcon", new SwingLazyStatic(pathName+".GEBIcons", "getFileViewComputer"),
				"FileView.hardDriveIcon", new SwingLazyStatic(pathName+".GEBIcons", "getFileViewDriver"),
				"FileView.floppyDriveIcon", new SwingLazyStatic(pathName+".GEBIcons", "getFileViewFloppyDriver"),
		};
		
		comboBox = new Object[]{
				"ComboBox.normalTop", new ColorUIResource(180,180,180),
				"ComboBox.normalBottom", new ColorUIResource(30,30,30),
				"ComboBox.pressTop", new ColorUIResource(160,160,160),
				"ComboBox.pressBottom", new ColorUIResource(0,0,0),
				"ComboBox.activeTop", new ColorUIResource(200,200,200),
				"ComboBox.activeBottom", new ColorUIResource(50,50,50),
				"ComboBox.focusTop", new ColorUIResource(175,175,175),
				"ComboBox.focusBottom", new ColorUIResource(35,35,35),
				"ComboBox.disableTop", new ColorUIResource(100,100,100),
				"ComboBox.disableBottom", new ColorUIResource(50,50,50),
				"ComboBox.textNormal", new ColorUIResource(0, 0, 0),
				"ComboBox.textActive", new ColorUIResource(0, 0, 0),
				"ComboBox.textPress", new ColorUIResource(255, 255, 255),
				"ComboBox.textDisable", new ColorUIResource(100, 100, 100),
				"ComboBox.focus", new ColorUIResource(0, 0, 0),
				"ComboBox.focusable", Boolean.FALSE, 
				"ComboBox.font", font,
				"ComboBox.defaultButtonFollowsFocus", Boolean.FALSE,
				"ComboBox.borderColor", new ColorUIResource(Color.BLACK),
				"ComboBox.borderSize", Float.valueOf(borderSize),
				"ComboBox.borderRound", Integer.valueOf(roundRectangleDefault),
				"ComboBox.border", GEBBorders.getComboBoxBorder(), //new SwingLazyStatic(pathName+".GEBBorders", "getComboBoxBorder"),
				"ComboBox.arrowButtonDirection", Integer.valueOf(SwingConstants.WEST),
	            "ComboBox.rollover", Boolean.TRUE,
				"ComboBox.opaque", Boolean.FALSE,
	            "ComboBox.focusInputMap", new UIDefaults.LazyInputMap(new Object[] {
	            		"SPACE", "pressed",
	            		"released SPACE", "released"
	            		}),
				
				
		
		};
		
		label = new Object[]{
				"Label.font", font,
		};
		
		spinner = new Object[] {
				"Spinner.normalTop", new ColorUIResource(180,180,180),
				"Spinner.normalBottom", new ColorUIResource(30,30,30),
				"Spinner.pressTop", new ColorUIResource(160,160,160),
				"Spinner.pressBottom", new ColorUIResource(0,0,0),
				"Spinner.activeTop", new ColorUIResource(200,200,200),
				"Spinner.activeBottom", new ColorUIResource(50,50,50),
				"Spinner.disableTop", new ColorUIResource(100,100,100),
				"Spinner.disableBottom", new ColorUIResource(50,50,50),
				"Spinner.textNormal", new ColorUIResource(0, 0, 0),
				"Spinner.arrowNormal", new ColorUIResource(0, 0, 0),
				"Spinner.arrowActive", new ColorUIResource(255, 255, 255),
				"Spinner.arrowPress", new ColorUIResource(255, 255, 0),
				"Spinner.focus", new ColorUIResource(0, 0, 0),
				"Spinner.focusable", Boolean.FALSE, 
				"Spinner.font", font,
				"Spinner.defaultButtonFollowsFocus", Boolean.FALSE,
				"Spinner.borderColor", new ColorUIResource(Color.BLACK),
				"Spinner.borderSize", Float.valueOf(1.5f),
	            "Spinner.rollover", Boolean.TRUE,
				"Spinner.border", GEBBorders.getSpinnerBorder(),
				"Spinner.borderRound", Integer.valueOf(roundRectangleDefault),
				
				
		};
		
		slider = new Object[] {
				"Slider.normalTop", new ColorUIResource(180,180,180),
				"Slider.normalBottom", new ColorUIResource(30,30,30),
				"Slider.pressTop", new ColorUIResource(160,160,160),
				"Slider.pressBottom", new ColorUIResource(0,0,0),
				"Slider.activeTop", new ColorUIResource(200,200,200),
				"Slider.activeBottom", new ColorUIResource(50,50,50),
				"Slider.disableTop", new ColorUIResource(100,100,100),
				"Slider.disableBottom", new ColorUIResource(50,50,50),
				"Slider.defaultButtonFollowsFocus", Boolean.FALSE,
				"Slider.borderColor", new ColorUIResource(Color.BLACK),
				"Slider.borderSize", Float.valueOf(borderSize),
	            "Slider.rollover", Boolean.TRUE,
				"Slider.borderRound", Integer.valueOf(roundRectangleDefault),
				
				
		};
		
		table.putDefaults(button);
		table.putDefaults(arrowButton);
		table.putDefaults(menuItem);
		table.putDefaults(scrollBar);
		table.putDefaults(checkBox);
		table.putDefaults(comboBox);
		table.putDefaults(toggleButton);
		table.putDefaults(popupMenu);
		table.putDefaults(label);
		table.putDefaults(spinner);
		table.putDefaults(slider);
		
		table.putDefaults(textDefaults);

		table.putDefaults(fileChooserDefaults);
		table.putDefaults(fileView);
		
	}
	
	public static Integer getTeste(){
		return Integer.valueOf(10);
	}
	
	public static void updateStyles(Component c) {
		    if (c instanceof JComponent) {
		        // Yes, this is hacky. A better solution is to get the UI
		        // and cast, but JComponent doesn't expose a getter for the UI
		        // (each of the UIs do), making that approach impractical.
		        String name = c.getName();
		        c.setName(null);
		        if (name != null) {
		            c.setName(name);
		        }
		        ((JComponent)c).revalidate();
		    }
		    Component[] children = null;
		    if (c instanceof JMenu) {
		        children = ((JMenu)c).getMenuComponents();
		    }
		    else if (c instanceof Container) {
		        children = ((Container)c).getComponents();
		    }
		    if (children != null) {
		        for (Component child : children) {
		            updateStyles(child);
		        }
		    }
		    c.repaint();
		}
	
	@Override public String getDescription() {return "GEA Gradient Erik Animator, é o Look and Feel usado no programa 2D Animator";}
	@Override public String getID() {return "GEALookAndFeel";}
	@Override public String getName() {return "GEALookAndFeel";}
	@Override public boolean isNativeLookAndFeel() {return false;}
	@Override public boolean isSupportedLookAndFeel() {return true;}
	
}
