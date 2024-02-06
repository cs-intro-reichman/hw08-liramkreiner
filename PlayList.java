/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        if(this.size>=this.maxSize)
            return false;
        this.tracks[size]=track;
        this.size++;
        return true;
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() 
    {
        if(size==0)
            return"";
        String str = this.tracks[0].toString();
        for (int i=1;i<this.size ;i++ ) 
        {
            str+= "/n" + this.tracks[i].toString();
        }
        return str;
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() 
     {
        if(this.tracks[size-1]!=null&&size>0)
        {
            this.tracks[size-1]=null;
            size--;  
        }
     }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() 
    {   
        int sumedura = 0;
        for(int i=0;i<size;i++)
            sumedura += this.tracks[i].getDuration();
        return sumedura;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) 
    {
        for(int i=0;i<size;i++)
            if(this.tracks[i].getTitle()==title)
                return i;
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) 
    {
        this.size++;
        return true;
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) 
    {
        if(this.size==0||i>=this.maxSize)
            return;
        this.tracks[i]=null; 
        for (int k=i+1;k<size;k++ ) 
        {
            this.tracks[k-1]=this.tracks[k];
        }
        this.tracks[size-1]=null;
        size--;

    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) 
    {
        int index = this.indexOf(title);
        if(index>-1)
            this.remove(index);
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() 
    {
        if(this.tracks[0]!=null)
        {
            this.tracks[0]=null;
            size--;  
        }
        for (int i=1;i<size+1 ;i++ ) 
        {
            this.tracks[i-1]=this.tracks[i];
        }
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) 
     {
        if(this.maxSize<=this.size)
            return;
        int counter = 0;
        for(int i=this.size;i<this.maxSize;i++)
        {
            this.tracks[i]=other.getTrack(counter);
            counter++;
            this.size++;
        }

     }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) 
    {
        if(size<start||start>=maxSize||start<0)
            return -1;
        int min=this.tracks[start].getDuration();
        int index=start;
        for (int i=start;i<size;i++ ) 
        {
            if(this.tracks[i].getDuration()<min)
            {
                min=this.tracks[i].getDuration();
                index=i;
            }   
        }
        return index;
    }
    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        return tracks[minIndex(0)].getTitle();
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() 
    {
        
       for(int i=0;i<size;i++)
       {
            int index = this.minIndex(i);
            Track swipe = this.tracks[index];
            Track current = this.tracks[i];
            this.tracks[i]=swipe;
            this.tracks[index]=current;
       }
    }
}
