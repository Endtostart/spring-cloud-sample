package zksupport;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;
import utils.JsonUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ZkWorker {

    private ZkConfig zkConfig;
    private ZooKeeper client;

    public ZkWorker(String server, Watcher watcher) throws IOException {
        new ZooKeeper(zkConfig.getServer(), zkConfig.getTimeOut(), watcher);
    }

    public ZkWorker(ZooKeeper client) {
        this.client = client;
    }

    public ZkWorker(ZkConfig zkConfig) throws IOException {
        new ZkWorker(zkConfig, null);
    }

    public ZkWorker(ZkConfig zkConfig, Watcher watcher) throws IOException {
        this.zkConfig = zkConfig;
        build(watcher);
    }

    private void build(Watcher watcher) throws IOException {
        client = new ZooKeeper(zkConfig.getServer(), zkConfig.getTimeOut(), watcher);
    }


    public List<String> getChilds(String path) {
        try {
            return client.getChildren(path, null);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getValue(String path) {
        return getValue(path, null);
    }

    public String getValue(String path, Watcher watcher) {
        return getValue(path, watcher, null);
    }

    public <T> T getValue(String path, Watcher watcher, Stat stat) {
        try {
            byte[] data = client.getData(path, watcher, stat);
            String json = String.valueOf(data);
            if (stat == null) {
                return (T) json;
            }
            stat = JsonUtils.json2Object(json, Stat.class);
            return (T) stat;
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String createEp(String path, String value) {
        return create(path, value, CreateMode.EPHEMERAL);
    }

    public String createPeAndSe(String path, String value) {
        return create(path, value, CreateMode.PERSISTENT_SEQUENTIAL);
    }

    public String createEpAndSe(String path, String value) {
        return create(path, value, CreateMode.EPHEMERAL_SEQUENTIAL);
    }

    public String create(String path, String value, List<ACL> acls, CreateMode mode) {
        if (Objects.isNull(path) || path.length() < 0) {
            return null;
        }
        if (Objects.isNull(value) || value.length() < 0) {
            return null;
        }

        try {
            return client.create(path, value.getBytes(), acls, mode);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String create(String path, String value, CreateMode mode) {
        return create(path, value, ZooDefs.Ids.OPEN_ACL_UNSAFE, mode);
    }

    public String create(String path, String value) {
        return create(path, value, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    public ZooKeeper getClient() {
        return client;
    }

    public void setClient(ZooKeeper client) {
        this.client = client;
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        String host = "127.0.0.1:2181";
        ZkWorker wocker = new ZkWorker(host,new ZkWatcher());
        List<String> ss = wocker.getChilds("/lock");
        System.out.println("/lock 子节点：" + ss.toString());
        String value = wocker.createEpAndSe("/lock/", "hello");
        System.out.println("创建节点：" + value);
        value = wocker.createEpAndSe("/lock/", "x");
        System.out.println("创建节点：" + value);
        value = wocker.createEpAndSe("/lock/", "y");
        System.out.println("创建节点：" + value);
        value = wocker.createEpAndSe("/lock/", "z");
        System.out.println("创建节点：" + value);
        ss = wocker.getChilds("/lock");
        System.out.println("更新后 /lock 子节点数据：" + ss);
    }
}


