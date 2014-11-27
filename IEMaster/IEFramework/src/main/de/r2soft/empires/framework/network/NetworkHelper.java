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

package de.r2soft.empires.framework.network;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import com.google.common.reflect.ClassPath;

import de.r2soft.empires.framework.players.Player;

/**
 * This is a small helper class taking care of package registrations and utilities to use by both the Kryo Client and
 * Kryo Server.
 * 
 * @author Katharina Fey
 */
public class NetworkHelper {

  /**
   * Registers all package types that are being exchanged by the server and client.
   * 
   * @param endpoint
   *          Is the object holding an instance of kryo to have classes registered to.
   */
  public static void register(EndPoint ep) {
	System.out.println("Attempting to register classes");
	Kryo k = ep.getKryo();

	List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
	classLoadersList.add(ClasspathHelper.contextClassLoader());
	classLoadersList.add(ClasspathHelper.staticClassLoader());

	Reflections er = new Reflections(new ConfigurationBuilder()
		.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
		.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("de.r2soft.empires.framework.network"))));

	Set<Class<?>> empiresClasses = er.getSubTypesOf(Object.class);

	// for (Class<?> c : empiresClasses)
	// k.register(c);

	k.register(ConnectionPackage.class);
	k.register(HandshakePackage.class);
	k.register(Host.class);
	k.register(Host.IP.class);

	// Reflections ar = new Reflections(new ConfigurationBuilder()
	// .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
	// .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
	// .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("org.apache.log4j"))));
	//
	// Set<Class<?>> apacheClasses = ar.getSubTypesOf(Object.class);
	//
	// for (Class<?> p : apacheClasses)
	// k.register(p);

	/** Now registering those pesky types that I didn't write myself */
	// k.register(Player.class);

	k.register(java.util.HashSet.class);
	k.register(java.util.Vector.class);
	// k.register(org.apache.log4j.Logger.class);
	// k.register(org.apache.log4j.spi.RootLogger.class);
	// k.register(org.apache.log4j.helpers.AppenderAttachableImpl.class);
	// k.register(org.apache.log4j.ConsoleAppender.class);
	// k.register(org.apache.log4j.helpers.OnlyOnceErrorHandler.class);

	System.out.println("Registered classes without error (possibly)");
  }
}
