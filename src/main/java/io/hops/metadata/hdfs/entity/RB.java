/*
 * Copyright (C) 2015 hops.io.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.hops.metadata.hdfs.entity;

/**
 * For records of replica's where we store both the storageId and the
 * datanodeUuid
 */
public abstract class RB extends ReplicaBase implements Comparable<ReplicaBase> {
  
  protected String datanodeUuid;

  protected RB(String datanodeUuid, int storageId, long blockId, int inodeId) {
    super(storageId, blockId, inodeId);
    this.datanodeUuid = datanodeUuid;
  }
  
  /**
   * @return the datanodeUuid
   */
  public String getDatanodeUuid() {
    return datanodeUuid;
  }
  
  /**
   * @param datanodeUuid
   *     the datanodeUuid to set
   */
  public void setDatanodeUuid(String datanodeUuid) {
    this.datanodeUuid = datanodeUuid;
  }
}
