package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Role;

public class RoleDB {
    
    public List<Role> getAll(int roleId) throws Exception {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String sql = "SELECT * FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while (rs.next()) {
                String roleName = rs.getString(2);
                Role role = new Role(roleId, roleName);
                roles.add(role);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }

        return roles;
    }
    
    // other methods (check the sql string)
    public Role get(int roleId) throws Exception {
        Role role = null;
        ConnectionPool conPool = ConnectionPool.getInstance();
        Connection con = conPool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            if(rs.next()){
                String roleName = rs.getString(2);                
                role = new Role(roleId, roleName);
            }
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            conPool.freeConnection(con);
        }
        
        return role;
    }

    public void insert(Role role) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "INSERT INTO role (role_id, role_name) VALUES (?, ?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role.getRoleId());
            ps.setString(2, role.getRoleName());          
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void update(Role role) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "UPDATE role SET role_name=? WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role.getRoleId());
            ps.setString(2, role.getRoleName());            
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

    public void delete(Role role) throws Exception {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        String sql = "DELETE FROM role WHERE role_id=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role.getRoleId());
            ps.executeUpdate();
        } finally {
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
    }

}