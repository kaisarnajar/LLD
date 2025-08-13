import java.util.Stack;

class TextMemento
{
    private final String text;

    public TextMemento(String text)
    {
        this.text = text;
    }

    public String getText()
    {
        return text;
    }
}

class TextEditor
{
    private String text = "";

    public void setText(String newText)
    {
        this.text = newText;
    }

    public String getText()
    {
        return text;
    }

    public TextMemento save()
    {
        return new TextMemento(text);
    }

    public void restore(TextMemento memento)
    {
        this.text = memento.getText();
    }
}

class TextHistory
{
    Stack<TextMemento> stk;

    TextHistory()
    {
        stk = new Stack<>();
    }

    public void save(TextEditor editor)
    {
        stk.push(editor.save());
    }

    void restore(TextEditor editor)
    {
        if(!stk.empty())
        {
            editor.restore(stk.pop());
        }
        else
        {
            System.out.println("No history to undo");
        }
    }
}

public class MementoDesignPattern
{
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        TextHistory history = new TextHistory();

        editor.setText("Hello");
        System.out.println(editor.getText());
        history.save(editor);

        editor.setText("Hello World");
        System.out.println(editor.getText());
        history.save(editor);

        editor.setText("Hello World Kaisar");
        history.save(editor);
        System.out.println(editor.getText());

        history.restore(editor);
        System.out.println(editor.getText());

        history.restore(editor);
        System.out.println(editor.getText());

        history.restore(editor);
        System.out.println(editor.getText());

        history.restore(editor);
        System.out.println(editor.getText());
    }
}
