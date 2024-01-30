/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import entity.Categories;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author huytu
 */
public class DAOCategories extends DBconnect {

    public List<Categories> getAll() {
        List<Categories> list = new ArrayList<>();
        String sql = "SELECT [CategoryID]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Description]\n"
                + "  FROM [dbo].[Categories]";
        try (PreparedStatement st = conn.prepareStatement(sql);
                ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Categories c = new Categories(rs.getInt("CategoryID"), rs.getString("CategoryName"),
                        rs.getString("Description"));
                list.add(c);
            }
        } catch (SQLException e) {
            // before
            // System.out.println(e);
            logger.log(Level.SEVERE, "An SQL exception occured: "+ e.getMessage(),e);
        }
           
        return list;
    }

    public Categories getCategorybyCategoryID(int id) {
        String sql = "SELECT [CategoryID]\n"
                + "      ,[CategoryName]\n"
                + "      ,[Description]\n"
                + "  FROM [dbo].[Categories]\n"
                + "  WHERE CategoryID=" + id;
        try {
            ResultSet rs = getData(sql);
            //before
            // if (rs.next()) {
            //     Categories c = new Categories(rs.getInt("CategoryID"),
            //             rs.getString("CategoryName"),
            //             rs.getString("Description"));
            //     return c;
            // }
            if (rs.next()) {
                return new Categories(rs.getInt("CategoryID"),
                rs.getString("CategoryName"),
                rs.getString("Description"));
            }
        } catch (SQLException e) {

        }
        
        return null;
    }
    private static final Logger logger = Logger.getLogger(DAOCategories.class.getName());
}