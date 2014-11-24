/* #########################################################################
 * Copyright (c) 2014 RANDOM ROBOT SOFTWORKS
 * (See @authors file)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ######################################################################### */

package de.r2soft.empires.server.client;

import java.awt.Dimension;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import de.r2soft.empires.framework.network.Packet;
import de.r2soft.empires.framework.network.Packet1Connect;
import de.r2soft.empires.framework.network.Packet2ClientConnected;
import de.r2soft.empires.framework.network.Packet3ClientDisconnect;
import de.r2soft.empires.framework.network.Packet4Chat;

/**
 * @author ***REMOVED*** <***REMOVED***>
 */
public class ClientMain implements ActionListener {

  private static final JFrame frame = new JFrame("Chat Client");
  private static final JTextArea textArea = new JTextArea();
  private static final JTextField textField = new JTextField(25);
  private static final JButton sendButton = new JButton("Send");

  private final Scanner scanner = new Scanner(System.in);
  Client client;

  private String username;

  public ClientMain() {
	client = new Client();
	client.start();

	try {
	  client.connect(5000, "127.0.0.1", 23900, 23901);
	}
	catch (IOException e) {
	  JOptionPane.showMessageDialog(null, "Server is not reachable. Can not connect!");
	  return;
	}

	username = JOptionPane.showInputDialog("Please enter your username.");

	client.getKryo().register(Packet.class);
	client.getKryo().register(Packet1Connect.class);
	client.getKryo().register(Packet2ClientConnected.class);
	client.getKryo().register(Packet3ClientDisconnect.class);
	client.getKryo().register(Packet4Chat.class);

	client.addListener(new Listener() {
	  public void received(Connection connection, Object object) {
		if (object instanceof Packet2ClientConnected) {
		  Packet2ClientConnected p2 = (Packet2ClientConnected) object;
		  textArea.append(p2.clientName + " connected.\n");
		}
		else if (object instanceof Packet3ClientDisconnect) {
		  Packet3ClientDisconnect p3 = (Packet3ClientDisconnect) object;
		  System.out.println("Client " + p3.clientName + " disconnected");
		  textArea.append(p3.clientName + " disconnected.\n");
		}
		else if (object instanceof Packet4Chat) {
		  Packet4Chat p4 = (Packet4Chat) object;
		  textArea.append(p4.username + ": " + p4.message + "\n");
		}
	  }
	});

	Packet1Connect p1 = new Packet1Connect();
	p1.userame = username;
	client.sendTCP(p1);

	frame.setSize(450, 375);
	frame.setLocationRelativeTo(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	Panel p = new Panel();

	sendButton.addActionListener(this);
	textArea.setEditable(false);
	textArea.setWrapStyleWord(true);
	textArea.setLineWrap(true);
	JScrollPane areaScrollPane = new JScrollPane(textArea);
	areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	areaScrollPane.setPreferredSize(new Dimension(430, 275));

	p.add(areaScrollPane);
	p.add(textField);
	p.add(sendButton);

	frame.add(p);
	frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent arg0) {
	String message = textField.getText();

	if (!message.equalsIgnoreCase("")) {
	  Packet4Chat p4 = new Packet4Chat();
	  p4.username = username;
	  p4.message = message;
	  client.sendTCP(p4);
	  textField.setText("");
	}
  }

  public static void main(String[] args) {
	new ClientMain();
  }

}
