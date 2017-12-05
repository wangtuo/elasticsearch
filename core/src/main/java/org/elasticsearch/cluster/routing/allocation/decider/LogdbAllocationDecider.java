/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
 
package org.elasticsearch.cluster.routing.allocation.decider;

import org.elasticsearch.cluster.routing.ShardRouting;
import org.elasticsearch.cluster.routing.allocation.RoutingAllocation;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.regex.Regex;
import org.elasticsearch.common.settings.Settings;
import org.joda.time.DateTime;

/**
 * Created by tuo on 2017/11/9.
 */
public class LogdbAllocationDecider extends AllocationDecider{
    @Inject
    public LogdbAllocationDecider(Settings settings) {
        super(settings);
    }

    @Override
    public Decision canRebalance(ShardRouting shardRouting, RoutingAllocation allocation) {
        DateTime curIndexTime = Regex.ExtractDateTimeOfDay(shardRouting.getIndex());
        if(curIndexTime != null && Regex.isAfterZeroTimeOfToday(curIndexTime)) {
            return Decision.NO;
        }
        return super.canRebalance(shardRouting, allocation);
    }

}
