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

import io.hops.metadata.common.FinderType;

public class ExcessReplica implements Comparable<ReplicaBase> {
  
  protected String datanodeUuid;
  protected long blockId;
  private int inodeId;

  public ExcessReplica(String uuid, long blockId, int inodeId) {
    this.datanodeUuid = uuid;
    this.blockId = blockId;
    this.inodeId = inodeId;
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
  
  /**
   * @return the blockId
   */
  public long getBlockId() {
    return blockId;
  }
  
  /**
   * @param blockId
   *     the blockId to set
   */
  public void setBlockId(long blockId) {
    this.blockId = blockId;
  }
  
  @Override
  public int hashCode() {
    int hash = 7;
    hash = 59 * hash + this.datanodeUuid.hashCode();
    hash = 59 * hash + (int) (this.blockId ^ (this.blockId >>> 32));
    return hash;
  }
  
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final ExcessReplica other = (ExcessReplica) obj;
    if (! this.datanodeUuid.equals(other.getDatanodeUuid())) {
      return false;
    }
    if (this.blockId != other.blockId) {
      return false;
    }
    return true;
  }
  
  public int getInodeId() {
    return inodeId;
  }
  
  public void setInodeId(int inodeId) {
    this.inodeId = inodeId;
  }
  
  @Override
  public int compareTo(ReplicaBase t) {
    if (this.equals(t)) {
      return 0;
    }
    
    if (t == null) {
      return 1;
    }
    
    if (this.hashCode() == t.hashCode()) {
      if (this.getBlockId() > t.getBlockId()) {
        return 1;
      } else {
        return -1;
      }
    } else {
      return Integer.compare(this.hashCode(), t.hashCode());
    }
  }

  public static enum Finder implements FinderType<ExcessReplica> {

    ByBlockIdDatanodeUuidAndINodeId,
    ByBlockIdAndINodeId,
    ByINodeId,
    ByINodeIds;

    @Override
    public Class getType() {
      return ExcessReplica.class;
    }

    @Override
    public Annotation getAnnotated() {
      switch (this) {
        case ByBlockIdDatanodeUuidAndINodeId:
          return Annotation.PrimaryKey;
        case ByBlockIdAndINodeId:
          return Annotation.PrunedIndexScan;
        case ByINodeId:
          return Annotation.PrunedIndexScan;
        case ByINodeIds:
          return Annotation.BatchedPrunedIndexScan;
        default:
          throw new IllegalStateException();
      }
    }

  }
}
