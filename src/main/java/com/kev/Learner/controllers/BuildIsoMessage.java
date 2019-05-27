package com.kev.Learner.controllers;

import com.kev.Learner.entities.IsoMessage;
import com.kev.Learner.repository.IsoMessageRepo;
import org.jpos.iso.ISOChannel;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISOPackager;
import org.jpos.iso.channel.XMLChannel;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.XMLPackager;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class BuildIsoMessage {

    private IsoMessageRepo msgRepo;

    public BuildIsoMessage(IsoMessageRepo msgRepo) {
        this.msgRepo = msgRepo;
    }

    public void createMessage() throws ISOException, IOException{
        // Build packager based on the xml file that contain the DE type
        GenericPackager packager = new GenericPackager("basic.xml");

        // Create the ISO message here
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);
        isoMsg.setMTI("0200");
        isoMsg.set(2, "114473659212");
        isoMsg.set(3, "201234");
        isoMsg.set(4, "10000");
        isoMsg.set(7, "110722180");
        isoMsg.set(11, "123456");
        isoMsg.set(41, "29110001");
        isoMsg.set(42, "T1LABS");
        isoMsg.set(44, "A5DFG4R");
        isoMsg.set(49, "870");
        isoMsg.set(93, "KEVIN");
        isoMsg.set(95, "1000000");
        isoMsg.set(105, "QWERTY 1234567890");

        // print the DE list
//        logISOMsg(isoMsg);

        // create the record in the database and return the id
        long msgId = persistIsoMsg(isoMsg);

        // Transmit the message to the ISO Server
        sendISOMsg(isoMsg, msgId);
    }

//    private void logISOMsg(ISOMsg msg) {
//        System.out.println("============ISO Message==================");
//        try {
//            System.out.println("MTI: " + msg.getMTI());
//            for (int i = 1; i <= msg.getMaxField(); i++) {
//                if(msg.hasField(i)) {
//                    System.out.println("    Field-" + i +" : " + msg.getString(i));
//                }
//            }
//        } catch (ISOException e) {
//            e.printStackTrace();
//        } finally {
//            System.out.println("------------------------------------");
//        }
//    }

    /*
    * This method will create the IsoMessage in the database
    * */
    private long persistIsoMsg(ISOMsg message) {
        IsoMessage msg = new IsoMessage();
        msg.setMti(message.getString(0));
        msg.setRequest(message.toString());
        try{
            msgRepo.save(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
        return msg.getId();
    }

    /*
    * This method will update the IsoMessage record in the database
    *  to capture the returned response
    * */
    private void updateDbIsoMessage(long id, ISOMsg message) {
        IsoMessage msg = msgRepo.getIsoMessageByMsgId(id);
        if (msg != null){
            msg.setResponse(message.toString());
            msgRepo.save(msg);
        }
    }

    /*
     * This method will take the created ISO message and send it to a listening server
     * @message: the ISOMsg we are sending to the server
     * @id: the ISOMsg identifier in the database to be used in updating the record
     * when we get the response
     * */
    private void sendISOMsg(ISOMsg message, long id) throws ISOException, IOException {
        System.out.println("============Sending ISO Message==================");
        ISOPackager packager = new XMLPackager();
        ISOChannel channel = new XMLChannel("localhost", 8000, packager);
        channel.connect();
        channel.send(message);
        ISOMsg response = channel.receive();
//        System.out.println(response);
        response.dump(System.out, "");

        // update the db record to capture this response
        updateDbIsoMessage(id, response);
    }
}
