package com.example;

import com.sobte.cqp.jcq.entity.*;
import static com.sobte.cqp.jcq.entity.ICQVer.CQAPIVER;
import static com.sobte.cqp.jcq.entity.IMsg.MSG_IGNORE;
import static com.sobte.cqp.jcq.event.JcqApp.CQ;
import com.sobte.cqp.jcq.event.JcqAppAbstract;
import static com.sobte.cqp.jcq.event.JcqAppAbstract.enable;

import javax.swing.*;

/**
 * ���ļ���JCQ���������<br>
 * <br>
 * <p>
 * ע���޸�json�е�class���������࣬�粻����������appid���أ����һ�������Զ���д����<br>
 * ����appid(com.example.demo) ������� com.example.Demo<br>
 * �ĵ���ַ�� https://gitee.com/Sobte/JCQ-CoolQ <br>
 * ���ӣ�https://cqp.cc/t/37318 <br>
 * ������������: {@link JcqAppAbstract#CQ CQ}({@link com.sobte.cqp.jcq.entity.CoolQ ��Q���Ĳ�����}),
 * {@link JcqAppAbstract#CC CC}({@link com.sobte.cqp.jcq.message.CQCode ��Q�������}),
 * ���幦�ܿ��Բ鿴�ĵ�
 */
public class Demo extends JcqAppAbstract implements ICQVer, IMsg, IRequest {

    /**
     * ��main�������Կ�����󻯵ļӿ쿪��Ч�ʣ����Ͷ�λ����λ��<br/>
     * ���¾���ʹ��Main�������в��Ե�һ�����װ���
     *
     * @param args ϵͳ����
     */
    public static void main(String[] args) {
        // CQ�˱���Ϊ�����������JCQ����ʱʵ������ֵ��ÿ����������ڲ����п�����CQDebug����������
        CQ = new CQDebug();//new CQDebug("Ӧ��Ŀ¼","Ӧ������") �����ô˹�������ʼ��Ӧ�õ�Ŀ¼
        CQ.logInfo("[JCQ] TEST Demo", "��������");// ���ھͿ�����CQ������ִ���κ���Ҫ�Ĳ�����
        // Ҫ�����������ʵ����һ���������
        Demo demo = new Demo();
        // �����������и���������,����JCQ���й��̣�ģ��ʵ�����
        demo.startup();// �������п�ʼ ����Ӧ�ó�ʼ������
        demo.enable();// �����ʼ����ɺ�����Ӧ�ã���Ӧ����������
        // ��ʼģ�ⷢ����Ϣ
        // ģ��˽����Ϣ
        // ��ʼģ��QQ�û�������Ϣ������QQȫ�����죬�������
        demo.privateMsg(0, 10001, 2234567819L, "����ͳ��", 0);
        // ģ��Ⱥ����Ϣ
        // ��ʼģ��Ⱥ����Ϣ
//        demo.groupMsg(0, 10006, 3456789012L, 3333333334L, "", "[CQ:at,qq=3615427102] ��ʾ�������й�����Դ��������", 0);
        // ......
        // �������ƣ����Ը���ʵ������޸Ĳ������ͷ�������Ч��
        // ��������β��������
        demo.disable();// ʵ�ʹ����г���������ᴥ��disable��ֻ���û��ر��˴˲���Żᴥ��
        demo.exit();// ���������н���������exit����
    }

    /**
     * ����󽫲������ �벻Ҫ�ڴ��¼���д��������
     *
     * @return ����Ӧ�õ�ApiVer��Appid
     */
    public String appInfo() {
        // Ӧ��AppID,����� http://d.cqp.me/Pro/����/������Ϣ#appid
        String AppID = "com.example.demo";// ��ס�������ļ���jsonҲҪʹ��appid���ļ���
        /**
         * ����������ֹ�����������κδ��룬���ⷢ���쳣����� ����ִ�г�ʼ���������� startup �¼���ִ�У�Type=1001����
         */
        return CQAPIVER + "," + AppID;
    }

    /**
     * ��Q���� (Type=1001)<br>
     * ���������ڿ�Q�����̡߳��б����á�<br>
     * ��������ִ�в����ʼ�����롣<br>
     * ����ؾ��췵�ر��ӳ��򣬷���Ῠס��������Լ�������ļ��ء�
     *
     * @return ��̶�����0
     */
    public int startup() {
        // ��ȡӦ������Ŀ¼(���财������ʱ���뽫����ע��)
        String appDirectory = CQ.getAppDirectory();
        // �����磺D:\CoolQ\app\com.sobte.cqp.jcq\app\com.example.demo\
        // Ӧ�õ��������ݡ����á����롿����ڴ�Ŀ¼��������û��������š�
        return 0;
    }

    /**
     * ��Q�˳� (Type=1002)<br>
     * ���������ڿ�Q�����̡߳��б����á�<br>
     * ���۱�Ӧ���Ƿ����ã������������ڿ�Q�˳�ǰִ��һ�Σ���������ִ�в���رմ��롣
     *
     * @return ��̶�����0�����غ��Q���ܿ�رգ��벻Ҫ��ͨ���̵߳ȷ�ʽִ���������롣
     */
    public int exit() {
        return 0;
    }

    /**
     * Ӧ���ѱ����� (Type=1003)<br>
     * ��Ӧ�ñ����ú󣬽��յ����¼���<br>
     * �����Q����ʱӦ���ѱ����ã����� {@link #startup startup}(Type=1001,��Q����)
     * �����ú󣬱�����Ҳ��������һ�Ρ�<br>
     * ��Ǳ�Ҫ����������������ش��ڡ�
     *
     * @return ��̶�����0��
     */
    public int enable() {
        enable = true;
        return 0;
    }

    /**
     * Ӧ�ý���ͣ�� (Type=1004)<br>
     * ��Ӧ�ñ�ͣ��ǰ�����յ����¼���<br>
     * �����Q����ʱӦ���ѱ�ͣ�ã��򱾺��������᡿�����á�<br>
     * ���۱�Ӧ���Ƿ����ã���Q�ر�ǰ�������������᡿�����á�
     *
     * @return ��̶�����0��
     */
    public int disable() {
        enable = false;
        return 0;
    }

    /**
     * ˽����Ϣ (Type=21)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subType �����ͣ�11/���Ժ��� 1/��������״̬ 2/����Ⱥ 3/����������
     * @param msgId ��ϢID
     * @param fromQQ ��ԴQQ
     * @param msg ��Ϣ����
     * @param font ����
     * @return ����ֵ*����*ֱ�ӷ����ı� ���Ҫ�ظ���Ϣ�������api����<br>
     * ���� ���� {@link IMsg#MSG_INTERCEPT MSG_INTERCEPT} - �ضϱ�����Ϣ�����ټ�������<br>
     * ע�⣺Ӧ�����ȼ�����Ϊ"���"(10000)ʱ������ʹ�ñ�����ֵ<br>
     * ������ظ���Ϣ������֮���Ӧ��/�������������� ���� {@link IMsg#MSG_IGNORE MSG_IGNORE} - ���Ա�����Ϣ
     */
    public int privateMsg(int subType, int msgId, long fromQQ, String msg, int font) {
        // ���ﴦ����Ϣ
        DataImportOther di = new DataImportOther();
        if (msg.contains("����")) {
            msg = msg.replace("����", "");
            di.hideMsg(msg);
            CQ.sendPrivateMsg(fromQQ, "������վ��" + msg + "���ɹ�");
        }
        if (msg.equals("����ͳ��")) {

            msg = di.al().toString().replace(", ", "\n").replace("[", "").replace("]", "");
            int n = di.al().size();
            CQ.sendPrivateMsg(fromQQ, "������ʡ�м�������Դ������������Ϊ0��������ܣ�\n" + msg + "\n���ƣ�" + n + "����վ");
        }

        if (msg.contains("��ʾ")) {
            msg = msg.replace("��ʾ", "");
            di.showMsg(msg);
            CQ.sendPrivateMsg(fromQQ, "��ʾ��վ��" + msg + "���ɹ�");
        }
        return MSG_IGNORE;

    }

    /**
     * Ⱥ��Ϣ (Type=2)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subType �����ͣ�Ŀǰ�̶�Ϊ1
     * @param msgId ��ϢID
     * @param fromGroup ��ԴȺ��
     * @param fromQQ ��ԴQQ��
     * @param fromAnonymous ��Դ������
     * @param msg ��Ϣ����
     * @param font ����
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int groupMsg(int subType, int msgId, long fromGroup, long fromQQ, String fromAnonymous, String msg,
            int font) {
        // �����Ϣ����������
        if (fromQQ == 80000000L && !fromAnonymous.equals("")) {
            // �������û���Ϣ�ŵ� anonymous ������
            Anonymous anonymous = CQ.getAnonymous(fromAnonymous);
        }

        // ����CQ�밸�� �磺[CQ:at,qq=100000]
        // ����CQ�� ���ñ���Ϊ CC(CQCode) �˱���רΪCQ�������ض���ʽ���˽����ͷ�װ
        // CC.analysis();// �˷�����CQ�����Ϊ��ֱ�Ӷ�ȡ�Ķ���
        // ������Ϣ�е�QQID
        //long qqId = CC.getAt(msg);// �˷���Ϊ��㷽������ȡ��һ��CQ:at���QQ�ţ�����ʱΪ��-1000
        //List<Long> qqIds = CC.getAts(msg); // �˷���Ϊ��ȡ��Ϣ�����е�CQ����󣬴���ʱ���� �ѽ���������
        // ������Ϣ�е�ͼƬ
        //CQImage image = CC.getCQImage(msg);// �˷���Ϊ��㷽������ȡ��һ��CQ:image���ͼƬ���ݣ�����ʱ��ӡ�쳣������̨������ null
        //List<CQImage> images = CC.getCQImages(msg);// �˷���Ϊ��ȡ��Ϣ�����е�CQͼƬ���ݣ�����ʱ��ӡ�쳣������̨������ �ѽ���������
        // ���ﴦ����Ϣ
        if (msg.contains(CC.at(3615427102L))) {
            DataImportOther di = new DataImportOther();
            if (msg.contains("����ͳ��")) {
                int n = di.al().size();
                msg = di.al().toString().replace(", ", "\n").replace("[", "").replace("]", "");
                CQ.sendGroupMsg(fromGroup, "������ʡ�м�������Դ����ȡ������Ϊ0��������ܣ�\n" + msg + "\n���ƣ�" + n + "����վ");
            }
        }
        return MSG_IGNORE;
    }

    /**
     * ��������Ϣ (Type=4)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subtype �����ͣ�Ŀǰ�̶�Ϊ1
     * @param msgId ��ϢID
     * @param fromDiscuss ��Դ������
     * @param fromQQ ��ԴQQ��
     * @param msg ��Ϣ����
     * @param font ����
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int discussMsg(int subtype, int msgId, long fromDiscuss, long fromQQ, String msg, int font) {
        // ���ﴦ����Ϣ

        return MSG_IGNORE;
    }

    /**
     * Ⱥ�ļ��ϴ��¼� (Type=11)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subType �����ͣ�Ŀǰ�̶�Ϊ1
     * @param sendTime ����ʱ��(ʱ���)// 10λʱ���
     * @param fromGroup ��ԴȺ��
     * @param fromQQ ��ԴQQ��
     * @param file �ϴ��ļ���Ϣ
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int groupUpload(int subType, int sendTime, long fromGroup, long fromQQ, String file) {
        GroupFile groupFile = CQ.getGroupFile(file);
        if (groupFile == null) { // ����Ⱥ�ļ���Ϣ�����ʧ��ֱ�Ӻ��Ը���Ϣ
            return MSG_IGNORE;
        }
        // ���ﴦ����Ϣ
        return MSG_IGNORE;
    }

    /**
     * Ⱥ�¼�-����Ա�䶯 (Type=101)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subtype �����ͣ�1/��ȡ������Ա 2/�����ù���Ա
     * @param sendTime ����ʱ��(ʱ���)
     * @param fromGroup ��ԴȺ��
     * @param beingOperateQQ ������QQ
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int groupAdmin(int subtype, int sendTime, long fromGroup, long beingOperateQQ) {
        // ���ﴦ����Ϣ

        return MSG_IGNORE;
    }

    /**
     * Ⱥ�¼�-Ⱥ��Ա���� (Type=102)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subtype �����ͣ�1/ȺԱ�뿪 2/ȺԱ����
     * @param sendTime ����ʱ��(ʱ���)
     * @param fromGroup ��ԴȺ��
     * @param fromQQ ������QQ(��������Ϊ2ʱ����)
     * @param beingOperateQQ ������QQ
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int groupMemberDecrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        // ���ﴦ����Ϣ

        return MSG_IGNORE;
    }

    /**
     * Ⱥ�¼�-Ⱥ��Ա���� (Type=103)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subtype �����ͣ�1/����Ա��ͬ�� 2/����Ա����
     * @param sendTime ����ʱ��(ʱ���)
     * @param fromGroup ��ԴȺ��
     * @param fromQQ ������QQ(������ԱQQ)
     * @param beingOperateQQ ������QQ(����Ⱥ��QQ)
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int groupMemberIncrease(int subtype, int sendTime, long fromGroup, long fromQQ, long beingOperateQQ) {
        // ���ﴦ����Ϣ

        return MSG_IGNORE;
    }

    /**
     * �����¼�-��������� (Type=201)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subtype �����ͣ�Ŀǰ�̶�Ϊ1
     * @param sendTime ����ʱ��(ʱ���)
     * @param fromQQ ��ԴQQ
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int friendAdd(int subtype, int sendTime, long fromQQ) {
        // ���ﴦ����Ϣ

        return MSG_IGNORE;
    }

    /**
     * ����-������� (Type=301)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subtype �����ͣ�Ŀǰ�̶�Ϊ1
     * @param sendTime ����ʱ��(ʱ���)
     * @param fromQQ ��ԴQQ
     * @param msg ����
     * @param responseFlag ������ʶ(����������)
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int requestAddFriend(int subtype, int sendTime, long fromQQ, String msg, String responseFlag) {
        // ���ﴦ����Ϣ

        /**
         * REQUEST_ADOPT ͨ�� REQUEST_REFUSE �ܾ�
         */
        // CQ.setFriendAddRequest(responseFlag, REQUEST_ADOPT, null); // ͬ������������
        return MSG_IGNORE;
    }

    /**
     * ����-Ⱥ��� (Type=302)<br>
     * ���������ڿ�Q���̡߳��б����á�<br>
     *
     * @param subtype �����ͣ�1/����������Ⱥ 2/�Լ�(����¼��)������Ⱥ
     * @param sendTime ����ʱ��(ʱ���)
     * @param fromGroup ��ԴȺ��
     * @param fromQQ ��ԴQQ
     * @param msg ����
     * @param responseFlag ������ʶ(����������)
     * @return ���ڷ���ֵ˵��, �� {@link #privateMsg ˽����Ϣ} �ķ���
     */
    public int requestAddGroup(int subtype, int sendTime, long fromGroup, long fromQQ, String msg,
            String responseFlag) {
        // ���ﴦ����Ϣ

        /**
         * REQUEST_ADOPT ͨ�� REQUEST_REFUSE �ܾ� REQUEST_GROUP_ADD Ⱥ���
         * REQUEST_GROUP_INVITE Ⱥ����
         */
        /*if(subtype == 1){ // ����ΪȺ�����ж��Ƿ�Ϊ����������Ⱥ
			CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_ADD, REQUEST_ADOPT, null);// ͬ����Ⱥ
		}
		if(subtype == 2){
			CQ.setGroupAddRequest(responseFlag, REQUEST_GROUP_INVITE, REQUEST_ADOPT, null);// ͬ�������Ⱥ
		}*/
        return MSG_IGNORE;
    }

    /**
     * ����������JCQ���̡߳��б����á�
     *
     * @return �̶�����0
     */
    public int menuA() {
        JOptionPane.showMessageDialog(null, "���ǲ��Բ˵�A��������������ش���");
        return 0;
    }

    /**
     * ���������ڿ�Q���̡߳��б����á�
     *
     * @return �̶�����0
     */
    public int menuB() {
        JOptionPane.showMessageDialog(null, "���ǲ��Բ˵�B��������������ش���");
        return 0;
    }

}
