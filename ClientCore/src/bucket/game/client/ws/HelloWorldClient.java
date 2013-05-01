/* 
 * Copyright (c) 2013 Leander Sabel
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package bucket.game.client.ws;



import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import server.ws.interfaces.HelloWorld;

import java.net.MalformedURLException;
import java.net.URL;

public class HelloWorldClient implements HelloWorld {
  private HelloWorld helloWorldService;

  /**
   * Default constructor
   * 
   * @param url
   *          The URL to the Hello World WSDL endpoint.
   */
  public HelloWorldClient(final URL wsdlUrl) {
	QName serviceName = new QName("http://webservices.samples.jboss.org/", "HelloWorldImplService");

	Service service = Service.create(wsdlUrl, serviceName);
	helloWorldService = service.getPort(HelloWorld.class);
	assert (helloWorldService != null);
	
	System.out.println(helloWorldService.sayHello("Bob"));
  }

  @Override
  public String sayHello(String name) {
	// TODO Auto-generated method stub
	return null;
  }
  
  public static void main(String[] args) throws MalformedURLException {
	HelloWorldClient client = new HelloWorldClient(new URL("http://localhost:8080/Server-WS/HelloWorld?wsdl"));
  }

}
