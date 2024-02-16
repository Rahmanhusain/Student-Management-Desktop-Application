//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.management.schoolmanagement;

import DButil.Dbconnection;
import com.management.schoolmanagement.model.Student;
import com.management.schoolmanagement.model.usermodel;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionStatus {
    Connection connection = Dbconnection.getconnection();

    public ConnectionStatus() {
        if (this.connection == null) {
            System.exit(1);
        }

    }

    public boolean isconnected() {
        return this.connection != null;
    }

    public boolean InsertStudent(long id, String name, String Class, String father,
                              String School, long tfee, long pfee, long dfee, String plan, String batch, String status,String subject) {
        String sql = "INSERT INTO  "+subject+" (ID,NAME,CLASS,FATHER,SCHOOL,TOATALFESS,FEEPAID,FEEDUE,FEEPLAN,BATCH,Status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            try {
                pstmt.setLong(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, Class);
                pstmt.setString(4, father);
                pstmt.setString(5, School);
                pstmt.setLong(6, tfee);
                pstmt.setLong(7, pfee);
                pstmt.setLong(8, dfee);
                pstmt.setString(9, plan);
                pstmt.setString(10, batch);
                pstmt.setString(11, status);
                pstmt.executeUpdate();
                System.out.println("data inserted successfully");
                return true;
            } catch (Throwable var23) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var22) {
                        var23.addSuppressed(var22);
                    }
                }

                throw var23;
            }

        } catch (SQLException var24) {
            System.out.println("failed to insert data due to: " + var24.getMessage());
        }
return false;
    }

    public void EditStudent(long id, String name, String Class, String father,
                            String School, long tfee, long pfee, long dfee, String plan, String batch, String status,Long sid,String subject) {
        String sql = "UPDATE  "+subject+"  SET ID = ?,NAME = ?,CLASS = ?,FATHER = ?,SCHOOL = ? ,TOATALFESS = ?,FEEPAID = ?,FEEDUE = ?,FEEPLAN = ?,BATCH = ?,Status = ? WHERE ID=?";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            try {
                pstmt.setLong(1, id);
                pstmt.setString(2, name);
                pstmt.setString(3, Class);
                pstmt.setString(4, father);
                pstmt.setString(5, School);
                pstmt.setLong(6, tfee);
                pstmt.setLong(7, pfee);
                pstmt.setLong(8, dfee);
                pstmt.setString(9, plan);
                pstmt.setString(10, batch);
                pstmt.setString(11, status);
                pstmt.setLong(12, sid);
                pstmt.executeUpdate();
                System.out.println("data edited successfully");
            } catch (Throwable var25) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var24) {
                        var25.addSuppressed(var24);
                    }
                }

                throw var25;
            }

            pstmt.close();
        } catch (SQLException var26) {
            System.out.println("failed to edit data due to: " + var26.getMessage());
        }

    }

    public ArrayList<Student> RetrieveStudent(String status,String Class,String plan,String batch,String subject) throws SQLException {
        ArrayList<Student> sdata = new ArrayList<>();

        if (status.equals("All Students") && Class.equals("all") && plan.equals("all") && batch.equals("all")){
            Statement stmt;
            stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM  "+subject+"  ORDER BY CLASS ;");

            while(rs.next()) {
                long i=1;

                sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                        rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                        rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                        rs.getString("BATCH"),rs.getString("Status")));

            }
        } else if (status.equals("All Students") && !Class.equals("all") && !plan.equals("all") && !batch.equals("all")) {

            String sql = "SELECT * FROM  "+subject+"  WHERE CLASS=? AND FEEPLAN=? AND BATCH=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, Class);
                    pstmt.setString(2, plan);
                    pstmt.setString(3, batch);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        } else if (status.equals("All Students") && !Class.equals("all") && plan.equals("all") && !batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE CLASS=? AND BATCH=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, Class);
                    pstmt.setString(2, batch);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        } else if (status.equals("All Students") && !Class.equals("all") && !plan.equals("all") && batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE CLASS=? AND FEEPLAN=?";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, Class);
                    pstmt.setString(2, plan);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (status.equals("All Students") && Class.equals("all") && !plan.equals("all") && !batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE FEEPLAN=? AND BATCH=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, plan);
                    pstmt.setString(2, batch);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (status.equals("All Students") && !Class.equals("all") && plan.equals("all") && batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE CLASS=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, Class);

                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (status.equals("All Students") && Class.equals("all") && !plan.equals("all") && batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE FEEPLAN=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, plan);

                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (status.equals("All Students") && Class.equals("all") && plan.equals("all") && !batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE BATCH=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, batch);

                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (!status.equals("All Students") && Class.equals("all") && plan.equals("all") && batch.equals("all")){
            String sql = "SELECT * FROM  "+subject+"  WHERE Status=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, status);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("ex student");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        } else if (!status.equals("All Students") && !Class.equals("all") && !plan.equals("all") && !batch.equals("all")) {

            String sql = "SELECT * FROM  "+subject+"  WHERE CLASS=? AND FEEPLAN=? AND BATCH=? AND Status=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, Class);
                    pstmt.setString(2, plan);
                    pstmt.setString(3, batch);
                    pstmt.setString(4, status);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));
                    }
                    System.out.println("executed all condition");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        } else if (!status.equals("All Students") && !Class.equals("all") && plan.equals("all") && !batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE CLASS=? AND BATCH=? AND Status=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, Class);
                    pstmt.setString(2, batch);
                    pstmt.setString(3, status);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        } else if (!status.equals("All Students") && !Class.equals("all") && !plan.equals("all") && batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE CLASS=? AND FEEPLAN=? AND Status=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, Class);
                    pstmt.setString(2, plan);
                    pstmt.setString(3, status);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (!status.equals("All Students") && Class.equals("all") && !plan.equals("all") && !batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE FEEPLAN=? AND BATCH=? AND Status=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, plan);
                    pstmt.setString(2, batch);
                    pstmt.setString(3, status);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (!status.equals("All Students") && !Class.equals("all") && plan.equals("all") && batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE CLASS=? AND Status=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, Class);
                    pstmt.setString(2, status);

                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (!status.equals("All Students") && Class.equals("all") && !plan.equals("all") && batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE FEEPLAN=? AND Status=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, plan);
                    pstmt.setString(2, status);
                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }else if (!status.equals("All Students") && Class.equals("all") && plan.equals("all") && !batch.equals("all")) {
            String sql = "SELECT * FROM  "+subject+"  WHERE BATCH=?;";

            try {
                PreparedStatement pstmt = this.connection.prepareStatement(sql);

                try {
                    pstmt.setString(1, batch);
                    pstmt.setString(2, status);

                    ResultSet rs = pstmt.executeQuery();
                    while(rs.next()) {
                        long i=1;
                        sdata.add(new Student(i,rs.getLong("ID"),rs.getString("NAME"),rs.getString("CLASS"),
                                rs.getString("FATHER"),rs.getString("SCHOOL"),rs.getLong("TOATALFESS"),
                                rs.getLong("FEEPAID"), rs.getLong("FEEDUE"),rs.getString("FEEPLAN"),
                                rs.getString("BATCH"),rs.getString("Status")));

                    }
                    System.out.println("data deleted successfully");
                } catch (Throwable var8) {
                    if (pstmt != null) {
                        try {
                            pstmt.close();
                        } catch (Throwable var7) {
                            var8.addSuppressed(var7);
                        }
                    }

                    throw var8;
                }

                pstmt.close();
            } catch (SQLException var9) {
                System.out.println("failed to delete data due to: " + var9.getMessage());
            }
        }
        return sdata;
    }

   
    

    public void Delstudent(long sid,String subject) {
        String sql = "DELETE FROM  "+subject+"  WHERE ID=?";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            try {
                pstmt.setLong(1, sid);
                pstmt.executeUpdate();
                System.out.println("data deleted successfully");
            } catch (Throwable var8) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            pstmt.close();
        } catch (SQLException var9) {
            System.out.println("failed to delete data due to: " + var9.getMessage());
        }

    }


    public int usercount() throws SQLException {
        Statement stmt;
        stmt = this.connection.createStatement();
        int count = 0;

        for(ResultSet rs4 = stmt.executeQuery("SELECT COUNT(user_id) AS csci FROM  Admin_login;"); rs4.next(); count = rs4.getInt("csci")) {
        }

        return count;
    }



    public void Insertuser(String pass, String name) {
        String sql = "INSERT INTO Admin_login (user_id,Password) VALUES(?,?)";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            try {
                pstmt.setString(1, name);
                pstmt.setString(2, pass);
                pstmt.executeUpdate();
                System.out.println("data inserted user successfully");
            } catch (Throwable var10) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var9) {
                        var10.addSuppressed(var9);
                    }
                }

                throw var10;
            }

            pstmt.close();
        } catch (SQLException var11) {
            System.out.println("failed to insert user data due to: " + var11.getMessage());
        }

    }

    public void Edituser(String pass, String sid) {
        String sql = "UPDATE Admin_login SET Password = ? WHERE user_id=?";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            try {
                pstmt.setString(1, pass);
                pstmt.setString(2, sid);
                pstmt.executeUpdate();
                System.out.println("data user edited successfully");
            } catch (Throwable var9) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var8) {
                        var9.addSuppressed(var8);
                    }
                }

                throw var9;
            }

            pstmt.close();
        } catch (SQLException var10) {
            System.out.println("failed to edit user data due to: " + var10.getMessage());
        }

    }

    public void Deluser(String sid) {
        String sql = "DELETE FROM Admin_login WHERE user_id=?";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            try {
                pstmt.setString(1, sid);
                pstmt.executeUpdate();
                System.out.println("data deleted user successfully");
            } catch (Throwable var7) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var6) {
                        var7.addSuppressed(var6);
                    }
                }

                throw var7;
            }

            pstmt.close();
        } catch (SQLException var8) {
            System.out.println("failed to delete user data due to: " + var8.getMessage());
        }

    }

    public ArrayList<usermodel> Retrieveuser() {
        ArrayList<usermodel> sdata = new ArrayList<>();
        String sql = "SELECT * FROM Admin_login;";

        try {
            PreparedStatement pstmt = this.connection.prepareStatement(sql);

            try {
                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {
                    sdata.add(new usermodel(rs.getString("user_id"), rs.getString("Password")));
                }
            } catch (Throwable var8) {
                if (pstmt != null) {
                    try {
                        pstmt.close();
                    } catch (Throwable var7) {
                        var8.addSuppressed(var7);
                    }
                }

                throw var8;
            }

            pstmt.close();
        } catch (SQLException var9) {
            System.out.println(var9.getMessage());
        }

        return sdata;
    }

    public ArrayList<usermodel> Retrieveuser2() throws SQLException {
        ArrayList<usermodel> sdata = new ArrayList<>();
        Statement stmt;
        stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Admin_login;");

        while(rs.next()) {
            sdata.add(new usermodel(rs.getString("user_id"), rs.getString("Password")));
            System.out.println(rs.getString("user_id")+" "+ rs.getString("Password"));
        }

        return sdata;
    }
}
