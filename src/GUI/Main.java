package GUI;

public class Main {
    public static void main(String[] args)
    { FirstFrame frame=new FirstFrame();
        frame.RemoveRow();
        frame.LoadFile();
        frame.AddRow();
        frame.SaveFile();
        frame.ShowInvoiceDetails();
        frame.SaveFileInvoice();

    }

}