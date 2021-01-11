import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.util.List;

public class TextAreaDialogWrapper extends DialogWrapper {

    public TextAreaDialogWrapper() {
        super(true);
        init();
        setTitle("Convert HTTP Request to cURL");
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        JPanel dialogPanel = new JPanel(new BorderLayout());

        JTextArea textArea = new JTextArea(
                "GET {{host}}/api/v1/chips\n" +
                        "Content-Type: application/json");
        
        textArea.setPreferredSize(new Dimension(600, 400));
        dialogPanel.add(textArea, BorderLayout.CENTER);
        return dialogPanel;
    }

    @NotNull
    @Override
    protected JPanel createButtonsPanel(@NotNull List<? extends JButton> buttons) {
        return super.createButtonsPanel(buttons);
    }

    boolean isOkEnabled() {
        return true;
    }

    @NotNull
    @Override
    protected Action[] createActions() {
        super.createActions();
        return new Action[] {new ConvertAction(), getCancelAction()};
    }

    private class ConvertAction extends DialogWrapperAction {
        protected ConvertAction() {
            super("Convert");
            putValue(Action.NAME, "Convert");
        }

        @Override
        protected void doAction(ActionEvent actionEvent) {
            if (doValidate() == null) {
                getOKAction().setEnabled(isOkEnabled());
            }
            System.out.println("MyCustomAction OK");
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection("MyCustomAction OK"), null);
            doOKAction();
        }
    }
}
