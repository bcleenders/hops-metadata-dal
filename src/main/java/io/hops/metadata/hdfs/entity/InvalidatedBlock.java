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

public class InvalidatedBlock extends RB {

  public static enum Finder implements FinderType<InvalidatedBlock> {

    ByBlockIdAndINodeId,
    ByINodeId,
    ByINodeIds,
    ByBlockIdDatanodeUuidAndINodeId,
    ByDatanodeUuid,
    All;

    @Override
    public Class getType() {
      return InvalidatedBlock.class;
    }

    @Override
    public Annotation getAnnotated() {
      switch (this) {
        case ByBlockIdAndINodeId:
          return Annotation.PrunedIndexScan;
        case ByINodeId:
          return Annotation.PrunedIndexScan;
        case ByINodeIds:
          return Annotation.BatchedPrunedIndexScan;
        case ByBlockIdDatanodeUuidAndINodeId:
          return Annotation.PrimaryKey;
        case ByDatanodeUuid:
          return Annotation.IndexScan;
        case All:
          return Annotation.FullTable;
        default:
          throw new IllegalStateException();
      }
    }

  }

  private long generationStamp;
  private long numBytes;

  public InvalidatedBlock(String datanodeUuid, long blockId, int inodeId) {
    super(datanodeUuid, blockId, inodeId);
  }

  public InvalidatedBlock(String datanodeUuid, long blockId, long
      generationStamp, long numBytes, int inodeId) {
    super(datanodeUuid, blockId, inodeId);
    this.generationStamp = generationStamp;
    this.numBytes = numBytes;
  }

  /**
   * @return the generationStamp
   */
  public long getGenerationStamp() {
    return generationStamp;
  }

  /**
   * @param generationStamp
   *     the generationStamp to set
   */
  public void setGenerationStamp(long generationStamp) {
    this.generationStamp = generationStamp;
  }

  /**
   * @return the numBytes
   */
  public long getNumBytes() {
    return numBytes;
  }

  /**
   * @param numBytes
   *     the numBytes to set
   */
  public void setNumBytes(long numBytes) {
    this.numBytes = numBytes;
  }
}
