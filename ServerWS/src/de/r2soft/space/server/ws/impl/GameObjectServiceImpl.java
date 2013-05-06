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

package de.r2soft.space.server.ws.impl;

import java.util.HashSet;
import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import de.r2soft.space.framework.map.IntVec2;
import de.r2soft.space.framework.objects.GameObject;
import de.r2soft.space.framework.objects.PlayerObject;
import de.r2soft.space.server.ws.interfaces.GameObjectService;

@WebService(serviceName = "GameObjectService", endpointInterface = "de.r2soft.space.server.ws.interfaces.GameObjectService", targetNamespace = "http://2rSoftworks.de/")
public class GameObjectServiceImpl implements GameObjectService {

  @Override
  @WebMethod
  public Set<GameObject> getGlobalGameObjects(Integer sessionID) {
	// TODO Get the objects from the Server
	return new HashSet<GameObject>();
  }

  @Override
  @WebMethod
  public Set<PlayerObject> getPlayerObjects(Integer sessionID) {
	// TODO Get the objects from the Server
	return new HashSet<PlayerObject>();
  }

  @Override
  @WebMethod
  public Set<PlayerObject> getObjectsInSystem(Integer sessionID, IntVec2 system) {
	// TODO Get the objects from the Server
	return new HashSet<PlayerObject>();
  }

}
