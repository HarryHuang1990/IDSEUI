import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * This class demonstrates JFace's IconAndMessageDialog class
 */
public class DumbUser extends ApplicationWindow {
  /**
   * DumbUser constructor
   */
  public DumbUser() {
    super(null);
  }

  /**
   * Runs the application
   */
  public void run() {
    // Don't return from open() until window closes
    setBlockOnOpen(true);

    // Open the main window
    open();

    // Dispose the display
    Display.getCurrent().dispose();
  }

  /**
   * Creates the main window's contents
   * 
   * @param parent the main window
   * @return Control
   */
  protected Control createContents(Composite parent) {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(1, true));

    // Create the button
    Button show = new Button(composite, SWT.NONE);
    show.setText("Show");

    final Shell shell = parent.getShell();

    // Display the dialog
    show.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent event) {
        // Create and show the dialog
        DumbMessageDialog dlg = new DumbMessageDialog(shell);
        dlg.open();
      }
    });

    parent.pack();
    return composite;
  }

  /**
   * The application entry point
   * 
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    new DumbUser().run();
  }
}

/**
 * This class demonstrates the IconAndMessageDialog class
 */
class DumbMessageDialog extends IconAndMessageDialog {
  public static final int I_DUNNO_ID = IDialogConstants.CLIENT_ID;
  public static final String I_DUNNO_LABEL = "I Dunno";

  // The image
  private Image image;

  // The label for the "hidden" message
  private Label label;

  /**
   * DumbMessageDialog constructor
   * 
   * @param parent the parent shell
   */
  public DumbMessageDialog(Shell parent) {
    super(parent);

    // Create the image
    try {
      image = new Image(parent.getDisplay(), new FileInputStream("icons/alt_window16.gif"));
    } catch (FileNotFoundException e) {}

    // Set the default message
    message = "Are you sure you want to do something that dumb?";
  }

  /**
   * Sets the message
   * 
   * @param message the message
   */
  public void setMessage(String message) {
    this.message = message;
  }

  /**
   * Closes the dialog
   * 
   * @return boolean
   */
  public boolean close() {
    if (image != null) image.dispose();
    return super.close();
  }

  /**
   * Creates the dialog area
   * 
   * @param parent the parent composite
   * @return Control
   */
  protected Control createDialogArea(Composite parent) {
    createMessageArea(parent);

    // Create a composite to hold the label
    Composite composite = new Composite(parent, SWT.NONE);
    GridData data = new GridData(GridData.FILL_BOTH);
    data.horizontalSpan = 2;
    composite.setLayoutData(data);
    composite.setLayout(new FillLayout());

    // Create the label for the "hidden" message
    label = new Label(composite, SWT.LEFT);

    return composite;
  }

  /**
   * Creates the buttons
   * 
   * @param parent the parent composite
   */
  protected void createButtonsForButtonBar(Composite parent) {
    createButton(parent, IDialogConstants.YES_ID, IDialogConstants.YES_LABEL,
        true);
    createButton(parent, IDialogConstants.NO_ID, IDialogConstants.NO_LABEL, false);
    createButton(parent, I_DUNNO_ID, I_DUNNO_LABEL, false);
  }

  /**
   * Handles a button press
   * 
   * @param buttonId the ID of the pressed button
   */
  protected void buttonPressed(int buttonId) {
    // If they press I Dunno, close the dialog
    if (buttonId == I_DUNNO_ID) {
      setReturnCode(buttonId);
      close();
    } else {
      // Otherwise, have some fun
      label.setText("Yeah, right. You know nothing.");
    }
  }

  /**
   * Gets the image to use
   */
  protected Image getImage() {
    return image;
  }
}