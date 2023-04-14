import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EMP_AWT extends Frame {
	Connection con;
	Statement st;
	ResultSet rs;
	PreparedStatement ps;

	EMP_AWT() {
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");

			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = st.executeQuery("select * from adi_emp");

		} catch (Exception e) {
			System.out.println(e);
		}
		Frame f = new Frame();
		Label l = new Label("Empno: ");
		l.setBounds(50, 100, 100, 30);
		TextField tf1 = new TextField();
		tf1.setBounds(180, 100, 100, 30);
		Label l1 = new Label("Name: ");
		l1.setBounds(50, 150, 100, 30);
		TextField tf2 = new TextField();
		tf2.setBounds(180, 150, 100, 30);
		Label l2 = new Label("Dept no: ");
		l2.setBounds(50, 200, 100, 30);
		TextField tf3 = new TextField();
		tf3.setBounds(180, 200, 100, 30);
		Label l3 = new Label("Job: ");
		l3.setBounds(50, 250, 100, 30);
		TextField tf4 = new TextField();
		tf4.setBounds(180, 250, 100, 30);
		Label l4 = new Label("Salary: ");
		l4.setBounds(50, 300, 100, 30);
		TextField tf5 = new TextField();
		tf5.setBounds(180, 300, 100, 30);
		Button b1 = new Button("First");
		b1.setBounds(50, 350, 50, 30);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					rs.first();
					tf1.setText(rs.getString(1));
					tf2.setText(rs.getString(2));
					tf3.setText(rs.getString(8));
					tf4.setText(rs.getString(3));
					tf5.setText(rs.getString(6));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button b2 = new Button("Next");
		b2.setBounds(100, 350, 50, 30);
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					rs.next();
					tf1.setText(rs.getString(1));
					tf2.setText(rs.getString(2));
					tf3.setText(rs.getString(8));
					tf4.setText(rs.getString(3));
					tf5.setText(rs.getString(6));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button b3 = new Button("Prior");
		b3.setBounds(150, 350, 50, 30);
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					rs.previous();
					tf1.setText(rs.getString(1));
					tf2.setText(rs.getString(2));
					tf3.setText(rs.getString(8));
					tf4.setText(rs.getString(3));
					tf5.setText(rs.getString(6));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button b4 = new Button("Last");
		b4.setBounds(200, 350, 50, 30);
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					rs.last();
					tf1.setText(rs.getString(1));
					tf2.setText(rs.getString(2));
					tf3.setText(rs.getString(8));
					tf4.setText(rs.getString(3));
					tf5.setText(rs.getString(6));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button b5 = new Button("Add");
		b5.setBounds(250, 350, 50, 30);
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					ps = con.prepareStatement("insert into adi_emp(empno,ename,dept_no,job,sal) values(?,?,?,?,?)");
					ps.setInt(1, Integer.parseInt(tf1.getText()));
					ps.setString(2, tf2.getText());
					ps.setInt(3, Integer.parseInt(tf3.getText()));
					ps.setString(4, tf4.getText());
					ps.setDouble(5, Double.parseDouble(tf5.getText()));
					ps.executeUpdate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		Button b6 = new Button("Edit");
		b6.setBounds(50, 400, 50, 30);
		Button b7 = new Button("Delete");
		b7.setBounds(100, 400, 50, 30);
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					ps = con.prepareStatement("delete from adi_emp where empno=(?)");
					ps.setInt(1, Integer.parseInt(tf1.getText()));
					ps.executeUpdate();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e);
				}
			}
		});
		Button b8 = new Button("Save");
		b8.setBounds(150, 400, 50, 30);
		Button b9 = new Button("Clear");
		b9.setBounds(200, 400, 50, 30);
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				try {
					rs.next();
					tf1.setText("");
					tf2.setText("");
					tf3.setText("");
					tf4.setText("");
					tf5.setText("");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Button b10 = new Button("Exit");
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});
		b10.setBounds(250, 400, 50, 30);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.add(l);
		f.add(tf1);
		f.add(l1);
		f.add(tf2);
		f.add(l2);
		f.add(tf3);
		f.add(l3);
		f.add(tf4);
		f.add(l4);
		f.add(tf5);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(b4);
		f.add(b5);
		f.add(b6);
		f.add(b7);
		f.add(b8);
		f.add(b9);
		f.add(b10);
		f.setLayout(null);
		f.setSize(600, 600);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		EMP_AWT e = new EMP_AWT();

	}

}
