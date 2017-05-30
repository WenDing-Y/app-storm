package com.tech.real;


import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class Main {	

	public static void main(String[] args) throws Exception {

		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("spout", new ReadSpout(), 2);
		builder.setBolt("area-bolt", new GetAreaBolt()).shuffleGrouping("spout");
		builder.setBolt("longitude-bolt", new WriteBolt()).shuffleGrouping("area-bolt");

		Config config = new Config();
		config.setNumWorkers(4);
	
		LocalCluster localCluster=new LocalCluster();
		localCluster.submitTopology("area-topology", config, builder.createTopology());

	}
}
