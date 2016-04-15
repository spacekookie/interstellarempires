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

package io.lonelyrobot.empires.framework.network;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author ***REMOVED***
 */
@EqualsAndHashCode(callSuper = false)
public @Data class Host {
  private String domain;
  private IP caller;

  public Host() {

  }

  public Host(String domain, IP caller) {
	this.domain = domain;
	this.caller = caller;
  }

  @Getter
  public static class IP {
	private int a, b, c, d;

	public IP() {

	}

	public IP(int a, int b, int c, int d) {
	  this.a = a;
	  this.b = b;
	  this.c = c;
	  this.d = d;
	}
  }
}
