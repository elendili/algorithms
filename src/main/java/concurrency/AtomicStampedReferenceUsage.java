package concurrency;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceUsage {

  @Test
  public void test() {
    AtomicStampedReference<String> asr = new AtomicStampedReference<>("A", 0);
    asr.getReference();
    Assertions.assertEquals("", "");
  }
}
