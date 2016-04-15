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

package io.lonelyrobot.empires.ergosphere;

import lombok.Data;
import lombok.EqualsAndHashCode;

import com.esotericsoftware.kryonet.Connection;

import io.lonelyrobot.empires.framework.network.Host;
import io.lonelyrobot.empires.framework.network.Host.IP;

/**
 * @author ***REMOVED***
 */

@EqualsAndHashCode(callSuper = false)
public @Data class ServerConnection extends Connection {
  private String player;
  private Host host;
  private long conTime;

  public ServerConnection() {
	new Host("", new IP(127, 0, 0, 1));
  }
}