/* #########################################################################
 * Copyright (c) 2013 Random Robot Softworks
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
 * 
 ######################################################################### */

package de.r2soft.space.server.ws.interfaces;

import java.util.Set;

import javax.jws.WebMethod;
import javax.jws.WebService;

import de.r2soft.space.framework.map.Map;
import de.r2soft.space.framework.objects.GameObject;
import de.r2soft.space.framework.objects.PlayerObject;
import de.r2soft.space.framework.types.IntVec2;

@WebService(targetNamespace = "http://2rSoftworks.de/")
public interface GameObjectService {

	@WebMethod
	public Set<GameObject> getGlobalGameObjects(Integer sessionID);

	@WebMethod
	public Set<PlayerObject> getPlayerObjects(Integer sessionID);

	@WebMethod
	public Set<PlayerObject> getObjectsInSystem(Integer sessionID, IntVec2 system);

}
