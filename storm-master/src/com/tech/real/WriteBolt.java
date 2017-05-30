package com.tech.real;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;

public class WriteBolt implements IBasicBolt {


	private static final long serialVersionUID = 1L;
	static Connection conn;
	static Statement st;
   
    

	
	public static void insert(String area) {
	     
		System.out.println("开始插入数据");
		try {
			String sql="update  position set num=num+1 where area='"+area+"'";
			int result=st.executeUpdate(sql); // exec sql 
			System.out.println("数据更新结果："+result+area);
		} catch (SQLException e) {
			System.out.println("update failed! " + e.getMessage());
		}
	}

	public void execute(Tuple tuple, BasicOutputCollector collector) {
		String word = tuple.getString(0);
	
		if (word!=null&&word.length()>0) {
			
			 word=ProvinceUtil.ProcessPro(word);
			 insert(word);
		}
      
	
	}
    @Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("area"));
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		conn = Dao.getConn(); // get connection
		try {
			st = (Statement) conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // create static sql statement
	}

	@Override
	public void cleanup() {
		try {
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
