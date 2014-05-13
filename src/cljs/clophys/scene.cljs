(ns clophys.scene)

(defrecord Scene
  [bodies contacts])

;; Constructors ===============================================================
(defn (make-scene [bodies contacts])
  (->Scene bodies contacts))
(def empty-scene (make-scene [] []))

;; Accessors ==================================================================
(defn bodies [scene] (:bodies scene))
(defn contacts [scene] (:contacts scene))

;; Add shape
(defn add-shape
  [scene shape x y]

(defn clear
  [scene]
  empty-scene)

(defn add-manifold
  [scene manifold])

(defn update-scene
  "Returns a new scene after some time period dt"
  [scene dt])

;; ======================
(defn integrate-forces [body dt]

// see http://www.niksula.hut.fi/~hkankaan/Homepages/gravity.html
void IntegrateForces( Body *b, real dt )
{
  if(b->im == 0.0f)
    return;

  b->velocity += (b->force * b->im + gravity) * (dt / 2.0f);
  b->angularVelocity += b->torque * b->iI * (dt / 2.0f);
}

void IntegrateVelocity( Body *b, real dt )
{
  if(b->im == 0.0f)
    return;

  b->position += b->velocity * dt;
  b->orient += b->angularVelocity * dt;
  b->SetOrient( b->orient );
  IntegrateForces( b, dt );
}

void Scene::Step( void )
{
  // Generate new collision info
  contacts.clear( );
  for(uint32 i = 0; i < bodies.size( ); ++i)
  {
    Body *A = bodies[i];

    for(uint32 j = i + 1; j < bodies.size( ); ++j)
    {
      Body *B = bodies[j];
      if(A->im == 0 && B->im == 0)
        continue;
      Manifold m( A, B );
      m.Solve( );
      if(m.contact_count)
        contacts.emplace_back( m );
    }
  }

  // Integrate forces
  for(uint32 i = 0; i < bodies.size( ); ++i)
    IntegrateForces( bodies[i], m_dt );

  // Initialize collision
  for(uint32 i = 0; i < contacts.size( ); ++i)
    contacts[i].Initialize( );

  // Solve collisions
  for(uint32 j = 0; j < m_iterations; ++j)
    for(uint32 i = 0; i < contacts.size( ); ++i)
      contacts[i].ApplyImpulse( );

  // Integrate velocities
  for(uint32 i = 0; i < bodies.size( ); ++i)
    IntegrateVelocity( bodies[i], m_dt );

  // Correct positions
  for(uint32 i = 0; i < contacts.size( ); ++i)
    contacts[i].PositionalCorrection( );

  // Clear all forces
  for(uint32 i = 0; i < bodies.size( ); ++i)
  {
    Body *b = bodies[i];
    b->force.Set( 0, 0 );
    b->torque = 0;
  }
}

(defn loop-physics!
  "Updates the physics"
  [dt accumulator]
  (let [curr-time (js/getTime)
        accumulator (clamp (+ accumulator (- curr-time (frame-start))))]
    (loop [accumulator accumulator]
      (if (>= accumulator dt)
          (do (update-physics! dt)
              (recur (- accumulator dt))
              accumulator)))))
