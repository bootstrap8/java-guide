package com.fit.kfk.send;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;

/**
 * @author Stone (300482)
 * @version 1.0
 * @since 2019-08-28
 */
@Slf4j
public class Sender {

    private KafkaProducer<String, String> producer;

    public Sender(String server) {
        Properties props = new Properties();
        props.put("bootstrap.servers", server);
//        props.put("bootstrap.servers", "192.168.56.4:9092");
        props.put("acks", "1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
//        List<String> interceptors = new ArrayList<>();
//        interceptors.add("huxi.test.producer.TimeStampPrependerInterceptor"); // interceptor 1
//        interceptors.add("huxi.test.producer.CounterInterceptor"); // interceptor 2
//        props.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
        producer = new KafkaProducer<>(props);
    }

    /**
     *
     */
    public void send(String topic, int cnt) {
        for (int i = 1; i <= cnt; i++) {
            String key = topic + "|" + i;
            String data = key;
            try {
                producer.send(new ProducerRecord<>(topic, key, data), (RecordMetadata metadata, Exception e) -> {
                    if (e != null) {
                        log.error("", e);
                    } else {
                        //log.info("send ok");
                    }
                });
                log.info("send to {} msg : {}", topic, data);
            } catch (Exception e) {
                log.error("send kfk msg error:", e);
            }
        }
        producer.flush();
    }


    public void send(String topic, String value) {
        try {
            producer.send(new ProducerRecord<>(topic, value), (RecordMetadata metadata, Exception e) -> {
                if (e != null) {
                    log.error("", e);
                } else {
                    //log.info("send ok");
                }
            });
            log.info("send to {} msg : {}", topic, value);
        } catch (Exception e) {
            log.error("send kfk msg error:", e);
        }
        producer.flush();
    }

    /**
     *
     */
    public void destroy() {
        if (producer != null) {
            producer.close();
        }
    }

    public static void main(String[] args) {
//        Sender sender = new Sender();
//        sender.send("foo", 100);
//        sender.send("bar", 100);
//        sender.destroy();

        Sender sender = new Sender("10.21.13.106:9092");
        String json = "{\"name\":\"/Alarm/Abims/FaultAlarm\",\"subNodes\":[{\"nodeAttrs\":{\"specialty\":\"3\",\"alarmactcount\":\"0\",\"alarmneid\":\"ONU.5226HWCSKONUdc65a5c5b733967f\",\"businesstype\":\"家宽\",\"alarmtext\":\"{\\\"specificProblemID\\\":\\\"45_772874247\\\",\\\"locationInfo\\\":\\\"机框\\u003d0, 槽\\u003d3, 子槽\\u003d65535, 端口\\u003d5, ONUID\\u003d4\\\",\\\"origSeverity\\\":4,\\\"neUID\\\":\\\"5226HWCSKOLTc79ed8840b7557d5\\\",\\\"objectUID\\\":\\\"5226HWCSKONUdc65a5c5b733967f\\\",\\\"addInfo\\\":\\\"OLTIP\\u003d172.37.0.97 设备类型\\u003dF663NV3a ONT描述信息(仅网管可见)\\u003d小区宽带H_天柱县_天柱县胡家坪FTTH小区四栋7单元1层9号_13595535580_70;DeviceIP:172.37.0.97\\\",\\\"neName\\\":\\\"天柱-鱼塘水库管理处-OLT017-HW-MA5683T\\\",\\\"layer\\\":1,\\\"objectType\\\":\\\"ONU\\\",\\\"alarmStatus\\\":1,\\\"alarmType\\\":\\\"qualityofServiceAlarm\\\",\\\"specificProblem\\\":\\\"(1)光纤断裂(2)光通路质量恶化(3)ONT硬件故障\\\",\\\"eventTime\\\":\\\"2020-07-31 09:53:58\\\",\\\"alarmId\\\":\\\"193515162\\\",\\\"objectName\\\":\\\"EMS;Huawei_U2000;ManagedElement;7340344;RU;/rack\\u003d1/shelf\\u003d0/slot\\u003d3/port\\u003d5/onu\\u003d4\\\",\\\"alarmTitle\\\":\\\"分支光纤断或OLT检测不到ONT的预期的光信号(LOSi/LOBi)\\\",\\\"alarmCheck\\\":\\\"null\\\",\\\"alarmSeq\\\":106601166,\\\"neType\\\":\\\"OLT\\\",\\\"holderType\\\":\\\"H805GPFD\\\"}\\nhostIp :10.198.116.155 APP_SN :IbM-HW-CS-Alarm\\nholdertype:H805GPFD\\nholderobjectname:EMS;Huawei_U2000;ManagedElement;7340344;RU;/rack\\u003d1/shelf\\u003d0/slot\\u003d3/port\\u003d5/onu\\u003d4\\nONU影响用户数(homecust_user):1\\n上联OLT名称(upper_olt):天柱-鱼塘水库管理处-OLT017-HW-MA5683T\\n上联OLT PORT端口(upper_olt_port):天柱-鱼塘水库管理处-OLT017-HW-MA5683T/0-0-3-5\\n二级分光器名称(upper_pos):天柱县胡家坪小区5栋4单元5栋4单元1楼POS002\\n接入方式(access_mode):FTTH\\n二级分光器天柱县胡家坪小区5栋4单元5栋4单元1楼POS002下挂ONU数(onu_num):16\\nalarmsort:ONU退服\\nOnuAcount:4059651789\\n影响用户数:1\\nsubspecialtyname:传输网\",\"onu_num\":\"16\",\"neid\":\"ONU.5226HWCSKONUdc65a5c5b733967f\",\"nebuilding\":\"null\",\"nealias\":\"小区宽带H_天柱县_天柱县胡家坪FTTH小区四栋7单元1层9号_13595535580_70\",\"neip\":\"null\",\"version\":\"U2000 V1R6C02\",\"alarmstatus\":\"1\",\"ackflag\":\"0\",\"customerclass\":\"\",\"cardmodel\":\"无\",\"vendorseverity\":\"提示告警\",\"daltime\":\"1596160390\",\"omc_name\":\"QDN-GPON-HW-U2000\",\"holderobjectname\":\"EMS;Huawei_U2000;ManagedElement;7340344;RU;/rack\\u003d1/shelf\\u003d0/slot\\u003d3/port\\u003d5/onu\\u003d4\",\"effectonequipment\":\"4\",\"relatedflag\":\"0\",\"jt_alarmseverity\":\"3\",\"upper_olt\":\"天柱-鱼塘水库管理处-OLT017-HW-MA5683T\",\"businesssystem\":\"\",\"orgserial\":\"193515162\",\"locatenename\":\"小区宽带H_天柱县_天柱县胡家坪FTTH小区四栋7单元1层9号_13595535580_70\",\"roomname\":\"天柱县资源点\",\"relationtype\":\"\",\"vendor\":\"华为\",\"alarmnetype\":\"ONU\",\"equipmentclass\":\"ONU\",\"buss_name\":\"0405-007-036-10-000153\",\"upper_pos\":\"天柱县胡家坪小区5栋4单元5栋4单元1楼POS002\",\"systemname\":\"传输网管\",\"alarmlogicsubclass\":\"通道\",\"upper_olt_port\":\"天柱-鱼塘水库管理处-OLT017-HW-MA5683T/0-0-3-5\",\"sendgroupflag\":\"0\",\"layerrate\":\"速率无关\",\"neroom\":\"100273\",\"nmsalarmtype\":\"1\",\"site\":\"\",\"alarmuniqueid\":\"guizhou_170_371769252\",\"alarmexplain\":\"无\",\"subspecialtyname\":\"传输网\",\"alarmseverity\":\"3\",\"btstype\":\"\",\"objectuid\":\"5226HWCSKONUdc65a5c5b733967f\",\"alarmsort\":\"ONU退服\",\"effectuser\":\"1\",\"eventtime\":\"1596160438\",\"locatenetype\":\"ONU\",\"acktime\":\"0\",\"vendoralarmid\":\"772874247\",\"ackuser\":\"\",\"jtrelatedflag\":0,\"alarmexplaintext\":\"分支光纤断或OLT检测不到ONT的预期的光信号(LOSi/LOBi)\",\"sheetsendstatus\":\"0\",\"sheetstatus\":\"0\",\"relationrule\":\"\",\"vendorserialno\":\"HW_CS_193515162\",\"businessinfo\":\"影响家宽客户数：xx\",\"holdertype\":\"H805GPFD\",\"vendortitle\":\"分支光纤断或OLT检测不到ONT的预期的光信号(LOSi/LOBi)\",\"alarmregion\":\"null\",\"sheetsystemname\":\"传输网管\",\"locateinfo\":\"机框\\u003d0, 槽\\u003d3, 子槽\\u003d65535, 端口\\u003d5, ONUID\\u003d4\",\"alarmsubtype\":\"0\",\"effectonbusiness\":\"4\",\"nename\":\"小区宽带H_天柱县_天柱县胡家坪FTTH小区四栋7单元1层9号_13595535580_70\",\"gather_id\":\"gz\",\"alarmtitle\":\"分支光纤断或OLT检测不到ONT的预期的光信号(LOSi/LOBi)\",\"probablecausetxt\":\"(1)光纤断裂(2)光通路质量恶化(3)ONT硬件故障\",\"ifabnormal\":\"0\",\"alarmprovince\":\"贵州省\",\"nettype_name\":\"\",\"remark1\":\"\",\"remark2\":\"\",\"eventid\":\"0\",\"groupcustomer\":\"\",\"sheetno\":\"\",\"autodealstatus\":\"0\",\"locateneid\":\"ONU.5226HWCSKONUdc65a5c5b733967f\",\"sheetsendfailres\":\"没有派单规则\",\"ifsoundalarm\":\"0\",\"clearuser\":\"\",\"vendoralarmtype\":\"(1)光纤断裂(2)光通路质量恶化(3)ONT硬件故障\",\"locatenestatus\":\"2\",\"locatenesubstatus\":\"0\",\"groupcustomerid\":\"\",\"emsid\":\"1735629257\",\"canceltime\":\"0\",\"specialtyex1\":\"传输网\",\"specialtyex2\":\"PON\",\"access_mode\":\"FTTH\",\"OnuAcount\":\"4059651789\",\"alarmmemo\":\"\",\"alarmlogicclass\":\"通信\",\"correlatealarmflag\":\"0\",\"homecust_user\":\"1\",\"nmsalarmid\":\"0405-007-036-10-000153\",\"vendorclearno\":\"HW_CS_193515162\",\"customerflag\":\"0\",\"omc_ip\":\"10.195.53.118\",\"alarmcounty\":\"市区\",\"businesslevel\":\"\",\"standardalarmname\":\"分支光纤断或OLT检测不到ONT的预期的光信号(LOSi/LOBi)\",\"isstandard\":\"1\"}}]}";
        sender.send("Alarm-All-Node", json);
        sender.destroy();
    }

}
