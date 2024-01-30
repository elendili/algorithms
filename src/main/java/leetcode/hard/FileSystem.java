package leetcode.hard;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileSystemWithTest {


    class FileSystem {
        record FsNode(Map<String, FsNode> children, StringBuilder fileContent, String name) {
            FsNode(String name) {
                this(new TreeMap<>(), new StringBuilder(), name);
            }
        }

        private final FsNode root;

        public FileSystem() {
            root = new FsNode("");
        }

        public List<String> ls(String path) {
            FsNode cur = getAndCreateFsNodeByPath(path);
            List<String> out;
            if (cur.fileContent.isEmpty()) {
                out = cur.children.keySet().stream().sorted().toList();
            } else {
                out = Collections.singletonList(cur.name);
            }
            return out;
        }

        private FsNode getAndCreateFsNodeByPath(String path) {
            FsNode cur = root;
            for (String name : path.split("/")) {
                if (!name.isEmpty()) {
                    if (!cur.children.containsKey(name)) {
                        cur.children.put(name, new FsNode(name));
                    }
                    cur = cur.children.get(name);
                }
            }
            return cur;
        }

        public void mkdir(String path) {
            getAndCreateFsNodeByPath(path);
        }

        public void addContentToFile(String path, String content) {
            FsNode cur = getAndCreateFsNodeByPath(path);
            cur.fileContent.append(content);
        }

        public String readContentFromFile(String path) {
            FsNode cur = getAndCreateFsNodeByPath(path);
            return cur.fileContent.toString();
        }
    }

    @Test
    public void test() {
        /**
         * Input
         * ["FileSystem", "ls", "mkdir", "addContentToFile", "ls", "readContentFromFile"]
         * [[], ["/"], ["/a/b/c"], ["/a/b/c/d", "hello"], ["/"], ["/a/b/c/d"]]
         * Output
         * [null, [], null, null, ["a"], "hello"]
         */
        FileSystem fs = new FileSystem();
        assertEquals("[]", fs.ls("/").toString());
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        assertEquals("[a]", fs.ls("/").toString());
        assertEquals("hello", fs.readContentFromFile("/a/b/c/d"));
    }
}