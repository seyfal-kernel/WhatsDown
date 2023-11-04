package org.thoughtcrime.securesms.util;

import androidx.annotation.NonNull;

public final class StringUtil {

  /**
   * @return The number of graphemes in the provided string.
   */
  public static int getGraphemeCount(@NonNull CharSequence text) {
    BreakIteratorCompat iterator = BreakIteratorCompat.getInstance();
    iterator.setText(text);
    return iterator.countBreaks();
  }
}
