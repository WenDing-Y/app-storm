package com.tech.real;

import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;
import http.HttpRequest;

public class GetAreaBolt implements IBasicBolt {

	

	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String line = tuple.toString();
		String all[] = line.split("\t", -1);
		String s=HttpRequest.sendGet("http://restapi.amap.com/v3/ip", 
				  "key=737431dbcc5162a77e4e852aa8138893&ip="+all[3]);
		
		collector.emit(new Values(s));
	}

	
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("area"));
	}

	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		
	}

	@Override
	public void cleanup() {
	}
}
