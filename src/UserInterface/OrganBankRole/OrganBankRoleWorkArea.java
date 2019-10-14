/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UserInterface.OrganBankRole;

import Business.EcoSystem;
import Business.Enterprise.Enterprise;
import Business.Network.Network;
import Business.Organization.DoctorOrganization;
import Business.Organization.OrganBankOrganization;
import Business.Organization.Organization;
import Business.SendEmail.SendEmail;
import Business.UserAccount.UserAccount;
import Business.WorkQueue.AcceptDonationWorkRequest;
import Business.WorkQueue.DoctorWorkRequest;
import Business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author deepankkhurana
 */
public class OrganBankRoleWorkArea extends javax.swing.JPanel {

    /**
     * Creates new form OrganBankRoleWorkArea
     */
    private final JPanel userProcessContainer;
    private final OrganBankOrganization organization;
    private final Enterprise enterprise;
    private final UserAccount userAccount;
    private final ArrayList<Organization> orgs;
    private final Network network;

    public OrganBankRoleWorkArea(JPanel userProcessContainer, UserAccount account, OrganBankOrganization organization, Enterprise enterprise, EcoSystem system, Network network) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.organization = organization;
        this.enterprise = enterprise;
        this.userAccount = account;
        this.network = network;
        this.orgs = new ArrayList<>();
        try {
            valueLabel.setText(organization.getName());
            networkLbl.setText(network.getName());
            for (Network n : system.getNetworkList()) {
                for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
                    for (Organization o : e.getOrganizationDirectory().getOrganizationList()) {
                        orgs.add(o);
                    }
                }
            }
            btnSend.setEnabled(false);
            
            populateRequestTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong..");
        }
    }

    public void populateRequestTable() {
        try {
            DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();

            model.setRowCount(0);
            for (Organization org : orgs) {
                if (org instanceof OrganBankOrganization) {
                    for (AcceptDonationWorkRequest request : org.getWorkQueue().getDonationWorkRequestList()) {
                        if (request.getDonor().getNetwork().equals(network.getName())) {
                            Object[] row = new Object[6];
                            row[0] = request;
                            row[1] = request.getDonor().getEmailid();
                            row[2] = request.getDonor().getContactNumber();
                            row[3] = request.getDonor().getAddress();
                            row[4] = request.getDonor().getOrganType();
                            row[5] = request.getSender() == null ? null : request.getSender().getEmployee().getName();
                            model.addRow(row);
                        }
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong..");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        valueLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        networkLbl = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnSend = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnTrack = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnAccept = new javax.swing.JButton();
        backgroundLbl = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 140, 190));

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Donations Received at");

        valueLabel.setForeground(new java.awt.Color(255, 255, 255));
        valueLabel.setText("<EnterpriseName>");

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Network");

        networkLbl.setForeground(new java.awt.Color(255, 255, 255));
        networkLbl.setText("<NetworkName>");

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Email Id", "Contact Number", "Address", "Organ Type", "Accepted By"
            }
        ));
        jScrollPane1.setViewportView(workRequestJTable);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Send to Doctor for registration");

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Track previously sent registration requests");

        btnTrack.setText("Track");
        btnTrack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrackActionPerformed(evt);
            }
        });

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Accept a request to send to doctor for registration");

        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(502, 502, 502)
                .addComponent(networkLbl))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel5))
            .addGroup(layout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addComponent(btnSend, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addComponent(btnTrack))
            .addGroup(layout.createSequentialGroup()
                .addGap(377, 377, 377)
                .addComponent(btnAccept, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel4))
            .addGroup(layout.createSequentialGroup()
                .addGap(619, 619, 619)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addComponent(valueLabel))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(backgroundLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 1450, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(networkLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(btnSend)
                .addGap(12, 12, 12)
                .addComponent(btnTrack))
            .addGroup(layout.createSequentialGroup()
                .addGap(541, 541, 541)
                .addComponent(btnAccept))
            .addGroup(layout.createSequentialGroup()
                .addGap(625, 625, 625)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(582, 582, 582)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(backgroundLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
       int selectedRow = workRequestJTable.getSelectedRow();
       if (selectedRow < 0) {
           JOptionPane.showMessageDialog(null, "Please select a donation to process.");
           return;
       }
       try {
           String approvedBy = String.valueOf(workRequestJTable.getValueAt(selectedRow, 5));
           AcceptDonationWorkRequest req = (AcceptDonationWorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
           String donationType = req.getDonor().getDonationType();
           String organtype = String.valueOf(workRequestJTable.getValueAt(selectedRow, 4));

           for (Organization org : orgs) {
               if (org instanceof DoctorOrganization) {
                   DoctorWorkRequest doctorRequest = new DoctorWorkRequest();
                   doctorRequest.setDonationType(donationType);
                   doctorRequest.setDescription(organtype);
                   doctorRequest.setStatus("Sent");
                   doctorRequest.setRequestedBy(approvedBy);
                   doctorRequest.setDonorEmailId(req.getDonor().getEmailid());
                   doctorRequest.setNetwork(network.getName());

                   org.getWorkQueue().getDoctorWorkRequestList().add(doctorRequest);

                   JOptionPane.showMessageDialog(null, "Request sent");
                   break;
               }

           }
       } catch (NullPointerException ne) {
           JOptionPane.showMessageDialog(null, "Error : "+ne.getMessage());
       } catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Error : "+e.getMessage());
       }
        
    }//GEN-LAST:event_btnSendActionPerformed

    private void btnTrackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrackActionPerformed
        try {
            CardLayout layout = (CardLayout) userProcessContainer.getLayout();
            for (Organization o : orgs) {
                if (o instanceof DoctorOrganization) {
                    userProcessContainer.add("TrackRequestStatusJPanel", new TrackRequestStatusJPanel(userProcessContainer, userAccount, enterprise, o.getWorkQueue().getDoctorWorkRequestList()));
                    layout.next(userProcessContainer);
                    break;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong..");
        }
    }//GEN-LAST:event_btnTrackActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        int selectedRow = workRequestJTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a donor to register.");
        }
        try {
            String acceptedBy = String.valueOf(workRequestJTable.getValueAt(selectedRow, 5));
            if (acceptedBy.equals("null")) {
                WorkRequest workRequest = (WorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
                workRequest.setSender(userAccount);
                populateRequestTable();
                btnSend.setEnabled(true);
                AcceptDonationWorkRequest req = (AcceptDonationWorkRequest) workRequestJTable.getValueAt(selectedRow, 0);

                //Send Email to donor
                String to = req.getDonor().getEmailid();
                String subject = "Donation accepted!";
                String message = "Thank you! Your donation is accepted. Your blood sample will be collected within 24 hours.";

                String user = "aeddemogss@gmail.com";
                String pass = "aed_gss123";

                SendEmail.send(to, subject, message, user, pass);
            } else {
                JOptionPane.showMessageDialog(null, "Already accepted.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Something went wrong..");
        }
    }//GEN-LAST:event_btnAcceptActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundLbl;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnSend;
    private javax.swing.JButton btnTrack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel networkLbl;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}
